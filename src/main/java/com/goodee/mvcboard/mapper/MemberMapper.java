package com.goodee.mvcboard.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.goodee.mvcboard.vo.Member;

@Mapper
public interface MemberMapper {
	Member loginMember(Member member);
}
