package com.gotcha.www.user.config;

public interface JwtProperties {
	String SECRET = "gotchaAccessToken";
	int EXPRIATION_TIME = 60000*30*1000; // 10분 (1000 = 1초)
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
	String STORAGE_STRING = "accessToken";
}
