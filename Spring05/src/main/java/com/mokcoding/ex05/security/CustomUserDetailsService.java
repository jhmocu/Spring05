package com.mokcoding.ex05.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mokcoding.ex05.domain.Member;
import com.mokcoding.ex05.domain.MemberRole;
import com.mokcoding.ex05.persistence.MemberMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class CustomUserDetailsService implements UserDetailsService {
		
    @Autowired
    private MemberMapper memberMapper;
    
    // UserDetailsService 인터페이스의 메서드를 구현하여 사용자 정보를 가져옴
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	log.info("loadUserByUsername()");
    	log.info(username);
        // 사용자 ID를 이용하여 회원 정보와 권한 정보를 조회
        Member member = memberMapper.selectMemberByMemberId(username);
        MemberRole role = memberMapper.selectRoleByMemberId(username);
        
        // 조회된 회원 정보가 없을 경우 예외 처리
        if (member == null) {
            throw new UsernameNotFoundException("없는 사용자입니다.");
        }
        
        // 회원의 역할을 Spring Security의 GrantedAuthority 타입으로 변환하여 리스트에 추가
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        
        // UserDetails 객체를 생성하여 회원 정보와 역할 정보를 담아 반환
        UserDetails userDetails = new User(member.getMemberName(), member.getMemberPw(), authorities);
        return userDetails;
    }

}
