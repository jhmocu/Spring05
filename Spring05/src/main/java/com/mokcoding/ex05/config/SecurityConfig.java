package com.mokcoding.ex05.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// Spring Security의 설정을 정의하는 클래스
// WebSecurityConfigurerAdapter를 상속하여 보안 기능을 구성
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// HttpSecurity 객체를 통해 HTTP 보안을 구성
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		// HTTP 보안 구성
		httpSecurity
		.authorizeRequests() // 요청에 대한 권한 부여
		// /example/main에  대한 모든 사용자 접근을 허용
		.antMatchers("/example/main").permitAll() 
		// /example/admin에 대한 ADMIN 사용자 접근을 허용
		.antMatchers("/example/admin").access("hasRole('ROLE_ADMIN')")  
		// /example/admin에 대한 MEMBER 사용자 접근을 허용
		.antMatchers("/example/member").access("hasRole('ROLE_MEMBER')");
		
		// antMatchers(pattern) : 특정 url 패턴에 맞는 경로 매핑
		// permitAll() : 모든 사용자 접근
		// access() : 특정 권한을 가진 사용자 접근
		// hasRole('ROLE_XXX') : XXX 등급으로 권한 설정
		
	
		httpSecurity.formLogin(); // 기본 formLogin 기능 사용
	}

	// AuthenticationManagerBuilder 객체를 통해 인증을 구성
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() // 애플리케이션 메모리에 사용자 정보 저장
		// user : member1, password : 1234, role : MEMBER
		.withUser("member1").password("{noop}1234").roles("MEMBER") 
		// user : admin1, password : 1234, role : ADMIN
		.and().withUser("admin1").password("{noop}1234").roles("ADMIN");
		
		// noop : 암호 인코딩을 설정하지 않음을 의미

	}

}