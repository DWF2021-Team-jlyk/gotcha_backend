package com.gotcha.www.user.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotcha.www.user.config.JwtProperties;
import com.gotcha.www.user.vo.PrincipalDetails;
import com.gotcha.www.user.vo.UserDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
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
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	
	private final AuthenticationManager authenticationManager;
	
	// /login 요청을 하면 로그인 시도를 위해서 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		log.info("JwtAuthenticationFilter: 로그인 시도 중...");
		
		// 1. username, password 받아서
		// request.getInputStream() byte안에 username과 password가 담겨있다.
		try {
			// 일반적으로 받는 방법
//			BufferedReader br = request.getReader();
//			String input = null;
//			while((input=br.readLine())!=null) {
//				System.out.println(input);
//			}
			
			// json 객체를 parsing 해준다
			ObjectMapper om = new ObjectMapper();
			UserDto userDto = om.readValue(request.getInputStream(), UserDto.class);
			log.info("userDto: " + userDto);
			
			// 토큰 생성
			UsernamePasswordAuthenticationToken authenticationToken = 
					new UsernamePasswordAuthenticationToken(userDto.getUser_id(), userDto.getUser_pwd());
			
			// 로그인 시도
			// PrincipalDetailsService -> loadUserByUsername() 함수가 실행된 후 정상이면
			// authentication이 리턴됨.
			// authentication -> 로그인한 정보가 담긴다.
			// DB에 있는 username과 password가 일치한다
			Authentication authentication = 
					authenticationManager.authenticate(authenticationToken);
			
			// authentication 객체가 session 영역에 저장됨. => 로그인이 되었다는 뜻.
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			
			// 값이 나온다는 것은 로그인이 정상적으로 되었다는 뜻.
			log.info("로그인 완료됨: "+principalDetails.getUserDto().getUser_id());
			
			// authentication 객체가 session영역에 저장을 해야하고 그 방법이 return 해주면 됨.
			// 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는 것이다.
			// 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없다 하지만 단지 권한 처리때문에 session 넣어준다.
			
			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		// 2. 정상인지 로그인 시도를 해본다. AuthenticationManager로 로그인 시도를 하면 
		// -> PrincipalDetailsService가 호출 -> loadUserByUsername()이 실행된다.
		
		// 3. PrincipalDetails를 세션에 담고(세션에 담지 안으면 권한 관리가 안된다.)
		
		// 4. JWT토큰을 만들어서 응답해주면 됨.
		return null;
	}
	
	// attemptAuthentication 실행 후 인증이 정상적으로 되었으면  successfulAuthentication 함수가 실행된다.
	// JWT 토큰을 만들어서 request용청한 사용자에게 JWT 토큰을 repsonse 해주면 된다.
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		log.info("successfulAuthentication 실행됨: 인증이 완료되었다는 뜻임");
		PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
		log.info("success login id :"+principalDetails.getUserDto().getUser_id());
		// RSA방식이 아닌 Hash 암호방식
		String jwtToken = JWT.create()
				.withSubject("gotcha토큰")
				.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPRIATION_TIME)) // => (10분) 만료 시간 (1000 = 1초)
				.withClaim("id",principalDetails.getUserDto().getUser_id())
				.sign(Algorithm.HMAC512(JwtProperties.SECRET));
		
		response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + jwtToken);
	}

}
