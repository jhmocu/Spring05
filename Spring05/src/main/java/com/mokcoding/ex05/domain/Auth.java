package com.mokcoding.ex05.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter 
@Setter
@ToString 
public class Auth {
	private String memberId;
	private String auth;
}
