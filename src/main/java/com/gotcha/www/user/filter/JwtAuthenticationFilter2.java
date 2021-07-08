package com.gotcha.www.user.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.gotcha.www.user.config.JwtProperties;
import com.gotcha.www.user.config.JwtTokenProvider;
import com.gotcha.www.user.vo.PrincipalDetails;

import lombok.RequiredArgsConstructor;

/* 
 * 작성일 : 2021-06-21
 * 작성자 : 장승업
 * login 요청 시 username, password의 정보를 토대로 
 * DB에 있는지 검색 후 있으면 token 생성 filter
*/

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음
// /login 요청해서 username,password 전송하면(post)
// UsernamePasswordAutheticationFilter 동작을 함
@RequiredArgsConstructor
public class JwtAuthenticationFilter2 extends UsernamePasswordAuthenticationFilter {
	
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter2.class);

	
    private final JwtTokenProvider jwtTokenProvider;
	
	
}
