package kr.spring.service;

import java.util.List;

import kr.spring.entity.Info;

public interface RecommendService {

	Info getRecommendUsers(Info info);

	List<String> getS3Photos(Info recommendUser);

	void saveLikeInteraction(String myUsername, String oppUsername, String like_dislike);

}
