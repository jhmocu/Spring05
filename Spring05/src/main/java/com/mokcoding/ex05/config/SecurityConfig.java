package com.mokcoding.ex05.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
		// /example/main에 대한 모든 사용자 접근을 허용
		.antMatchers("/example/main").permitAll() 
		// /example/admin에 대한 ADMIN 사용자 접근을 허용
		.antMatchers("/example/admin").access("hasRole('ROLE_ADMIN')")  
		// /example/admin에 대한 MEMBER 사용자 접근을 허용
		.antMatchers("/example/member").access("hasRole('ROLE_MEMBER')");
		
		httpSecurity.formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER").and().withUser("admin")
				.password("{noop}1234").roles("ADMIN");

	}

}