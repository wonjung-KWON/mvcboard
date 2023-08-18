package com.goodee.mvcboard.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.goodee.mvcboard.service.MemberService;
import com.goodee.mvcboard.vo.Member;

@Controller
public class LoginController {
	@Autowired
	private MemberService memberService;
	@GetMapping("/member/login")
	public String login() {
		
		return "/member/login";
	}
	
	@PostMapping("/member/login")
	public String login(Model model,
						HttpSession session,
						Member member) {
		// service(memberIdm memberPw) -> mapper ->  로그인 성공 유무 확인
		Member loginMember = memberService.loginMember(member);
		
		// 로그인 성공시
		session.setAttribute("loginMemberId", loginMember.getMemberId());
		model.addAttribute("loginMemberId", session.getAttribute("loginMemberId"));
		return "/home";
	}
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/home";
	}
}