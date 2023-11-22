package kr.spring.service;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import kr.spring.entity.Info;
import kr.spring.entity.Interaction;
import software.amazon.awssdk.services.accessanalyzer.endpoints.internal.Value.Str;

@Service
public class RecommendServiceImpl implements RecommendService {

	@Autowired
	DBService dbService;

	@Autowired
	AmazonS3 s3client;

	@Override
	public Info getRecommendUsers(Info info) {
		// info : 현재 세션에서 받아온 info.
		System.out.println("[RecommendServiceImpl][getRecommendUsers]");

		String myName = info.getUsername();

		DriverConfigLoader loader = dbService.getConnection();
		List<Info> allUsers = dbService.findAll(loader, Info.class);

		/*
		 * 내가 좋아요를 누르거나 싫어요를 누른 사람들은 정보가 뜨지 않아야함 type = 'like' 또는 'dislike' from_to =
		 * 'to'
		 */
		Map<String, Object> likeColumnValues = new HashMap<String, Object>();
		likeColumnValues.put("type", "like");
		likeColumnValues.put("from_to", "to");
		likeColumnValues.put("my_username", info.getUsername());

		Map<String, Object> dislikeColumnValues = new HashMap<String, Object>();
		dislikeColumnValues.put("type", "dislike");
		dislikeColumnValues.put("from_to", "to");
		dislikeColumnValues.put("my_username", info.getUsername());

		List<Interaction> likeInteractions = dbService.findAllByColumnValues(loader, Interaction.class,
				likeColumnValues);
		List<Interaction> dislikeInteractions = dbService.findAllByColumnValues(loader, Interaction.class,
				dislikeColumnValues);

		List<Interaction> allInteractions = Stream.concat(likeInteractions.stream(), dislikeInteractions.stream())
				.collect(Collectors.toList());

		List<String> alreadyDoneUsernames = allInteractions.stream().map(Interaction::getOpponent_username)
				.collect(Collectors.toList());
		
		// 필터링 조건을 적용하여 조건에 맞는 사용자만 추출
		List<Info> filteredUsers = allUsers.stream()
		                                   .filter(user -> !user.getUsername().equals(myName)) // 현재 사용자 제외
		                                   .filter(user -> user.getPhoto() != null) // 사진이 null이 아닌 사용자
		                                   .filter(user -> !user.getPhoto().equals("{1: \"default.png\"}")) // 기본 사진이 아닌 사용자
		                                   .filter(user -> !alreadyDoneUsernames.contains(user.getUsername())) // alreadyDoneUsernames에 포함되지 않은 사용자
		                                   .collect(Collectors.toList());

		// 랜덤으로 5명을 선택
		Collections.shuffle(filteredUsers);
		List<Info> recommendUsers = filteredUsers.stream().limit(5).collect(Collectors.toList());

		return recommendUsers.get(0);
	}

	@Override
	public List<String> getS3Photos(Info recommendUser) {

		Map<Integer, String> photoMap = recommendUser.getPhoto();

		List<String> imageDatas = new ArrayList<>();
		String bucketName = "simkoong-s3";
		String base64Encoded = null;
		if (photoMap != null) {
			for (int i = 1; i <= 4; i++) {
				String imagePath = photoMap.get(i);
				if (imagePath != null) {
					File file = new File(imagePath);
					String fileName = file.getName();
					try {
						S3Object s3object = s3client.getObject(bucketName, fileName);
						S3ObjectInputStream inputStream = s3object.getObjectContent();
						byte[] bytes = IOUtils.toByteArray(inputStream);
						base64Encoded = Base64.encodeBase64String(bytes);
						imageDatas.add(base64Encoded);
					} catch (Exception e) {
						// 파일이 존재하지 않을 때 빈 이미지 추가
						base64Encoded = ""; // 빈 문자열 또는 기본 이미지 URL 설정
						imageDatas.add(base64Encoded);
					}
				}
			}
		}

		return imageDatas;
	}

	@Override
	public void saveLikeInteraction(String myUsername, String oppUsername, String like_dislike) {
		System.out.println("[RecommendServiceImpl][saveLikeInteraction]");

		/*
		 * interaction 테이블에 좋아요 정보 집어 넣어야함 1. from / 2. to 로 나눠 두번 저장해야함
		 * 
		 * interaction_uuid : UUID.random() from_to : from, to 각각 정보 저장
		 * interaction_regdate : instant.now() type : like last_checked : null
		 * my_username : info.getUsername opponent_username : oppUserName type_uuid :
		 * null
		 */

		// from 먼저 저장
		Instant now = Instant.now();

		Interaction fromInteraction = new Interaction();
		fromInteraction.setFrom_to("from");
		fromInteraction.setInteraction_regdate(now);
		fromInteraction.setInteraction_uuid(UUID.randomUUID());
		fromInteraction.setMy_username(oppUsername);
		fromInteraction.setOpponent_username(myUsername);
		fromInteraction.setType(like_dislike); // like

		Interaction toInteraction = new Interaction();
		toInteraction.setFrom_to("to");
		toInteraction.setInteraction_regdate(now);
		toInteraction.setInteraction_uuid(UUID.randomUUID());
		toInteraction.setMy_username(myUsername);
		toInteraction.setOpponent_username(oppUsername);
		toInteraction.setType(like_dislike); // like

		DriverConfigLoader loader = dbService.getConnection();
		dbService.save(loader, Interaction.class, fromInteraction);
		dbService.save(loader, Interaction.class, toInteraction);

	}

	@Override
	public Boolean isCheckLikeMe(Info info, String oppUserName) {
		System.out.println("[RecommendServiceImpl][checkLikeMe]");
		
		// 만약 상대로부터 받은 좋아요가 있다면,
		Map<String, Object> interactionCV = new HashMap<String, Object>();
		interactionCV.put("from_to", "from");
		interactionCV.put("my_username", info.getUsername());
		interactionCV.put("opponent_username", oppUserName);
		interactionCV.put("type", "like");
		DriverConfigLoader loader = dbService.getConnection();
		List<Interaction> interactions = dbService.findAllByColumnValues(loader, Interaction.class, interactionCV);
		
		// 없다면 false, 있다면 true 반환
		if (interactions.size() == 0) {
			return false;
		} else {
			return true;
		}
		
	}

	@Override
	public UUID saveMatching(Info info, String oppUserName) {
		System.err.println("[RecommendServiceImpl][saveMatching]");
		
		DriverConfigLoader loader = dbService.getConnection();
		
		Interaction myMatching = new Interaction();
		Interaction oppMatching = new Interaction();
		/*
		 * 1. 내가 상대와 매칭됨,
		 * 2. 상대와 내가 매칭됨
		 * 이 두 조건이 전부 save 되어야한다.
		 * 공통분자 둘
		 *    1. type_uuid : 상대와 나의 매칭 방은 같아야함
		 *    2. type_regdate : 상대와 나의 매칭 시간은 같아야함
		 */
		
		UUID sharedTypeUuid = UUID.randomUUID();
		Instant sharedMatchingRegdate = Instant.now();
		String type = "matching";
		String from_to = "from";
		
		
		/*
		 * 내가 상대와 매칭됨.
		 * 1. my_username = info.getUsername()
		 * 2. opponent_username = oppUserName
		 * 3. interaction_uuid = UUID.random()
		 */
		
		myMatching.setFrom_to(from_to);
		myMatching.setInteraction_regdate(sharedMatchingRegdate);
		myMatching.setType(type);
		myMatching.setType_uuid(sharedTypeUuid);
		myMatching.setInteraction_uuid(UUID.randomUUID());
		myMatching.setMy_username(info.getUsername());
		myMatching.setOpponent_username(oppUserName);
	
		
		/*
		 * 상대가 나와 매칭됨.
		 * 1. my_username = oppUserName
		 * 2. opponent_username = info.getUsername()
		 * 3. interaction_uuid = UUID.random()
		 */
		
		oppMatching.setFrom_to(from_to);
		oppMatching.setInteraction_regdate(sharedMatchingRegdate);
		oppMatching.setType(type);
		oppMatching.setType_uuid(sharedTypeUuid);
		oppMatching.setInteraction_uuid(UUID.randomUUID());
		oppMatching.setMy_username(oppUserName);
		oppMatching.setOpponent_username(info.getUsername());
		
		dbService.save(loader, Interaction.class, myMatching);
		dbService.save(loader, Interaction.class, oppMatching);
		
		return sharedTypeUuid;
	
	}

}