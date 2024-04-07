package com.mokcoding.ex05.persistence;

import org.apache.ibatis.annotations.Mapper;

import com.mokcoding.ex05.domain.Member;

@Mapper
public interface MemberMapper {
	int insert(Member member);
}
