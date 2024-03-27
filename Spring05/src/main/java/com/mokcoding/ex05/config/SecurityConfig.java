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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/example/main").permitAll().antMatchers("/example/admin")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/example/member").access("hasRole('ROLE_MEMBER')");
		
		http.formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("member").password("{noop}1234").roles("MEMBER").and().withUser("admin")
				.password("{noop}1234").roles("ADMIN");

	}

}