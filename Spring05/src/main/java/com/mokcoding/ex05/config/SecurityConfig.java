package com.mokcoding.ex05.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


// Spring Security의 설정을 정의하는 클래스
// WebSecurityConfigurerAdapter를 상속하여 보안 기능을 구성
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// HttpSercurity 객체를 통해 HTTP 보안을 구성
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	// 모든 요청에 대해 권한을 설정
        http.authorizeRequests()
            // "/" 경로에 대한 요청은 모두 허용
            .antMatchers("/").permitAll()
            // 그 외의 모든 요청은 인증된 사용자만 접근할 수 있도록 설정
            .anyRequest().authenticated();
    }
    

}