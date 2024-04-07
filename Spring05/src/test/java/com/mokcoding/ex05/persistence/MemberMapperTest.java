package com.mokcoding.ex05.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mokcoding.ex05.config.RootConfig;
import com.mokcoding.ex05.domain.Member;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class) // 스프링 JUnit 실행 클래스 연결
@ContextConfiguration(classes = { RootConfig.class }) // 설정 파일 연결
@Log4j
public class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void test() {
		testInsertMember();
	}
	
	
	private void testInsertMember() {
		Member member = new Member();
		member.setMemberId("user1");
		member.setMemberPw(passwordEncoder.encode("pw1"));
		member.setMemberName("test1");
	
		memberMapper.insert(member);
	}
}
