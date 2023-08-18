package com.goodee.mvcboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goodee.mvcboard.mapper.MemberMapper;
import com.goodee.mvcboard.vo.Member;

@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	// login
	public Member loginMember(Member member) {
		Member loginMember = memberMapper.loginMember(member);
				return loginMember;
	}
}
