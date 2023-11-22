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

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import kr.spring.entity.Info;
import kr.spring.entity.Interaction;

@Service
public class RecommendServiceImpl implements RecommendService {

	@Autowired
	DBService dbService;
	
	@Autowired
	AmazonS3 s3client;

	@Override
	public Info getRecommendUsers(Info info) {
		System.out.println("[RecommendServiceImpl][getRecommendUsers]");

		String myName = info.getUsername();

		DriverConfigLoader loader = dbService.getConnection();
		List<Info> allUsers = dbService.findAll(loader, Info.class);

		// 필터링 조건을 적용하여 조건에 맞는 사용자만 추출
		List<Info> filteredUsers = allUsers.stream().filter(user -> !user.getUsername().equals(myName)) // 현재 사용자 제외
				.filter(user -> user.getPhoto() != null) // 사진이 null이 아닌 사용자
				.filter(user -> !user.getPhoto().equals("{1: \"default.png\"}")) // 기본 사진이 아닌 사용자
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
		 * interaction 테이블에 좋아요 정보 집어 넣어야함
		 * 1. from / 2. to 로 나눠 두번 저장해야함
		 * 
		 * interaction_uuid : UUID.random()
		 * from_to : from, to 각각 정보 저장
		 * interaction_regdate : instant.now()
		 * type : like
		 * last_checked : null
		 * my_username : info.getUsername
		 * opponent_username : oppUserName
		 * type_uuid : null
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
		dbService.save(loader , Interaction.class, fromInteraction);
		dbService.save(loader , Interaction.class, toInteraction);
		
		
	}

}