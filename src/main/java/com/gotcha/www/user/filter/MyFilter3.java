package com.gotcha.www.user.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter3 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 토큰:코스 => id, pw 정상적으로 들어와서 로그인 완료 되면 토큰을 만들어 주고 그걸 응답 해준다.
		// 요청할 때 마다 header에 Authorization에 value값으로 토큰을 가지고 온다.
		// 그때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증만 하면 된다.(RSA, HS256)
		if(req.getMethod().equals("POST")) {
			System.out.println("POST 요청됨");
			String headerAuth = req.getHeader("Authorization");
			System.out.println(headerAuth);
			System.out.println("필터1");
			
			if(headerAuth.equals("cos")) {
				chain.doFilter(req, res);
			}else {	
				PrintWriter out = res.getWriter();
				out.println("not allowed");
			}
		}
	}

}
