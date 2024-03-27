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
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
            .withUser("user")
            .password(passwordEncoder().encode("1234"))
            .roles("USER");
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 암호화 방식을 선택하여 PasswordEncoder를 반환합니다.
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
    	// 모든 요청에 대해 권한을 설정
        http.authorizeRequests()
            // "/" 경로에 대한 요청은 모두 허용
            .antMatchers("/").permitAll()
            // 그 외의 모든 요청은 인증된 사용자만 접근할 수 있도록 설정
            .anyRequest().authenticated()
	        .and()
	        .formLogin() // 폼 기반 인증을 활성화
	        .permitAll(); // 로그인 페이지는 모든 사용자에게 허용
	}

 
}