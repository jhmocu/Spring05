package com.mokcoding.ex05.config;

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
 
	// HttpSecurity 객체를 통해 HTTP 보안 기능을 구성
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity
		.authorizeRequests()
		// "/example/main" URL에 대한 모든 사용자 접근을 허용
		.antMatchers("/example/main").permitAll() 
		// "/example/admin" URL에 대한 ADMIN 사용자 접근을 허용
		.antMatchers("/example/admin").access("hasRole('ROLE_ADMIN')")  
		// "/example/admin" URL에 대한 MEMBER 사용자 접근을 허용
		.antMatchers("/example/member").access("hasRole('ROLE_MEMBER')");
		
		// antMatchers(pattern) : 특정 url 패턴에 맞는 경로 매핑
		// permitAll() : 모든 사용자 접근
		// access() : 특정 권한을 가진 사용자 접근
		// hasRole('ROLE_XXX') : XXX 등급으로 권한 설정
	
		// 접근 제한 경로 설정
		httpSecurity.exceptionHandling().accessDeniedPage("/access/accessDenied");
		
		httpSecurity.formLogin().loginPage("/access/login"); // 커스텀 로그인 url 설정
				
		httpSecurity.logout().logoutUrl("/access/logout") // logout url 설정
		.invalidateHttpSession(true); // 세션 무효화 설정
		
		// header 정보에 xssProtection 기능 설정
		httpSecurity.headers().xssProtection().block(true);
		httpSecurity.headers().contentSecurityPolicy("script-src 'self'");
	}

	// AuthenticationManagerBuilder 객체를 통해 인증 기능을 구성
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() // 애플리케이션 메모리에 사용자 정보 저장
//		 user : member1, password : 1234, role : MEMBER
		.withUser("member1").password("{noop}1234").roles("MEMBER")
//		 user : admin1, password : 1234, role : ADMIN
		.and().withUser("admin1").password("{noop}1234").roles("ADMIN");
		
		// noop : 암호 인코딩을 설정하지 않음을 의미

	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

}