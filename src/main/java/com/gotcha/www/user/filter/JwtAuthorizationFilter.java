package com.gotcha.www.user.filter;


/* 
 * 작성일시 : 2021-06-21
 * 작성자 : 장승업
 * token의 있는 유저 정보를 토대로
 * 작업하고자 하는 ws_id를 검색하여 권한을 관리
*/

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotcha.www.user.config.JwtProperties;
import com.gotcha.www.user.dao.UserDAO;
import com.gotcha.www.user.vo.PrincipalDetails;
import com.gotcha.www.user.vo.UserDto;
import com.gotcha.www.workList.vo.WorkListVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

// 시큐리티가 filter가지고 있는데 그 필터중에 BasicAuthenticationFilter라는 것이 있다.
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음.
// 만약에 권한이 인증이 필요한 주소가 아니라면 이 필터를 안탄다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{

	private final UserDAO userDAO;
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDAO userDAO) {
		super(authenticationManager);
		this.userDAO = userDAO;
	}
	
	// 인증이나 권한이 필요한 주소 요청이 있을 때 해당 필터를 타게 됨.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		super.doFilterInternal(request, response, chain);
		log.info("path:" + request.getServletPath());
		
		// get workspace id 
		ObjectMapper om = new ObjectMapper();
		WorkListVO workListVO = om.readValue(request.getInputStream(), WorkListVO.class);
		log.info("toString : " + workListVO.toString());
		String ws_id = Integer.toString(workListVO.getWs_id());
		String token = workListVO.getAccessToken();
		log.info("ws_id: " + workListVO.getWs_id());
		log.info("token: " + token);
		log.info("인증이나 권한이 필요한 주소 요청이 됨.");
		if(ws_id.equals(null) || ws_id.equals("")) {
			log.info("ws_id not value");
		}
//		String jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
//		log.info("jwtHeader : "+jwtHeader);
		
		// header가 있는지 확인
//		if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
//			PrintWriter out = response.getWriter();
//			out.print("<javascript>");
//			out.print("alert('null');");
//			out.print("</javascript>");
//			chain.doFilter(request, response);
//			return;
//		}
		
		if(token == null || !token.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}else if (token != null && validateToken(token)) {
			// JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
//			String jwtToken = request.getHeader(JwtProperties.HEADER_STRING).replace(JwtProperties.TOKEN_PREFIX, "");

			try {
				String jwtToken = token.replace(JwtProperties.TOKEN_PREFIX, "");
				log.info("[jwtToken] "+jwtToken);

				String userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
						.build().verify(jwtToken)
						.getClaim("id").asString();
				
				log.info("userId : " + userId);
				
				// 서명이 정상적으로 됨
				if(userId != null) {
					log.info("username 정상");
					UserDto userEntity = userDAO.findByWsUsername(userId,ws_id);
					log.info("userEntity: " + userEntity.getUser_id());
					log.info("userEntity: " + userEntity.getRole_type());
					PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
					log.info("principalDetails : " + principalDetails.getUsername()+".....");

					// Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
					Authentication authentication = 
							new UsernamePasswordAuthenticationToken(principalDetails,null,principalDetails.getAuthorities());
					
					// session 공간
					// 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch(ExpiredJwtException e) {
				e.printStackTrace();
				log.info("Token 만료 재로그인 요청합니다.");
				request.setAttribute("exception", "[TOKEN EXPRIRED] 재로그인 요청");
				return;
			}catch (JWTVerificationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			chain.doFilter(request, response);
		}
	
	}
	
	// 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
	
	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Authentication authResult) throws IOException {
		log.info("onSuccessfulAuthentication 성공");
		log.info(authResult.getAuthorities().toString());
		//super.onSuccessfulAuthentication(request, response, authResult);
	}

	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException {
		log.info("onUnsuccessfulAuthentication 실패");
		//super.onUnsuccessfulAuthentication(request, response, failed);
	}
	
	
}
