package com.mokcoding.ex05.domain;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter 
@Setter
@ToString 
public class CustomUser extends User {
	
	private String memberId;
	private String memberPw;
	private String memberName;
	private Date regDate;
	
	public CustomUser(String memberId, 
					 String memberPw,
					 String memberName, 
					 Date regDate, 
					 Collection<? extends GrantedAuthority> authorities) {
		super(memberId, memberPw, authorities);
		
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.regDate = regDate;
		
	}

}
