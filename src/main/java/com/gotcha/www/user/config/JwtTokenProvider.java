package com.gotcha.www.user.config;

import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.gotcha.www.user.service.PrincipalDetailsService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
	
	private String secretKey;
	
	private final PrincipalDetailsService principalDetailService;
	
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes());
	}
	
	public String createToken(String userPk) {
		Claims claims = Jwts.claims().setSubject(userPk);
		Date now = new Date();
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(new Date(now.getTime() + JwtProperties.EXPRIATION_TIME))
				.signWith(SignatureAlgorithm.HS256, secretKey)
				.compact();
	}
	
}
