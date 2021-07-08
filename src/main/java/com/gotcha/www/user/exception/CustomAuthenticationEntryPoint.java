package com.gotcha.www.user.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/* 
 * 작성일 : 2021-06-29
 * 작성자 : 장승업
 * 시큐리티 필터에서 발생하는 예외 처리
*/
@Component
//@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	
	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		String exception = (String)request.getAttribute("exception");
		log.info("entryPoint");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		int status = response.getStatus();
		
		log.info("[getStatus] "+status);
		
		response.sendRedirect("/user/accessDenied");
		log.debug("log: exception: {} ", exception);
		
	}

}
