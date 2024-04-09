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
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberMapper.selectMemberByMemberId(username);
		MemberRole role = memberMapper.selectRoleByMemberId(username);
		
		if (member == null) {
			throw new UsernameNotFoundException("없는 사용자입니다.");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		
		UserDetails userDetails = new User(member.getMemberName(), member.getMemberPw(), authorities);
		return userDetails;
	}

}
