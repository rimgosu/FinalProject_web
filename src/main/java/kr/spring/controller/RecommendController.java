package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.spring.entity.Info;
import kr.spring.service.RecommendService;

@Controller
public class RecommendController {

	@Autowired
	RecommendService recommendService;

	@GetMapping("/recommend")
	public String showRecommendPage(Model model, HttpSession session) {
		System.out.println("[RecommendController][/recommend]");

		Info info = (Info) session.getAttribute("mvo");

		// TODO : RecommendUser는 현재 랜덤으로 아무나 가져오는 매커니즘임.
		// 추후 이성추천 시스템과 필터링 시스템을 거쳐 가지고 오는 것으로 바뀌어야함.
		// TODO : 한 번 좋아요를 누르면 더 이상 추천에 뜨지 않음
		Info recommendUser = recommendService.getRecommendUsers(info);

		List<String> imageDatas = recommendService.getS3Photos(recommendUser);
		

		model.addAttribute("imageDatas", imageDatas);
		model.addAttribute("recommendUser", recommendUser);

		return "recommend";
	}
	
	@GetMapping("/recommendLike")
	public String like(HttpSession session, @RequestParam String oppUserName) {
		System.out.println("[RecommendController][/recommendLike]");
		
		Info info = (Info) session.getAttribute("mvo");
		
		// from, to 둘 다 저장
		recommendService.saveLikeInteraction(info.getUsername(), oppUserName, "like");
		
		return "redirect:/recommend";
	}

}
