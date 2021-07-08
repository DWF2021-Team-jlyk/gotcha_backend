package com.gotcha.www.user.filter;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotcha.www.user.config.JwtProperties;
import com.gotcha.www.user.config.RequestBodyWrapper;
import com.gotcha.www.user.dao.UserDAO;
import com.gotcha.www.user.vo.PrincipalDetails;
//import com.gotcha.www.user.filter.AuthException;
//import com.gotcha.www.user.filter.JwtException;
//import com.gotcha.www.user.filter.User;
import com.gotcha.www.user.vo.UserDto;
import com.gotcha.www.workList.vo.WorkListVO;

// 시큐리티가 filter가지고 있는데 그 필터중에 BasicAuthenticationFilter라는 것이 있다.
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음.
// 만약에 권한이 인증이 필요한 주소가 아니라면 이 필터를 안탄다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);
	
	private final UserDAO userDAO;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDAO userDAO) {
		super(authenticationManager);
		this.userDAO = userDAO;
	}

	// 인증이나 권한이 필요한 주소 요청이 있을 때 해당 필터를 타게 됨.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
//		super.doFilterInternal(request, response, chain);
		System.out.println("path: " + request.getServletPath());
		//System.out.println("request.getInputStream() : " + request.getInputStream());

		// get workspace id
		
		RequestBodyWrapper requestWrapper = new RequestBodyWrapper(request);
		log.info("[requestWrapper] " + requestWrapper.getInputStream().read());
		log.info("[requestWrapper] " + requestWrapper.getReader());
		log.info("[requestWrapper] " + requestWrapper.getUserPrincipal());
		
		ObjectMapper om = new ObjectMapper();
		WorkListVO workListVO = om.readValue(requestWrapper.getInputStream(), WorkListVO.class);
		log.info("[workListVO] "+workListVO);
		String ws_id = Integer.toString(workListVO.getWs_id());
		String token = workListVO.getAccessToken();
//		System.out.println("ws_id: " + workListVO.getWs_id());
		log.info("token: " + token);

		
		log.info("인증이나 권한이 필요한 주소 요청이 됨.");

//		String jwtHeader = request.getHeader("Authorization");
//		System.out.println("jwtHeader : "+jwtHeader);

		// header가 있는지 확인
//		if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
//			chain.doFilter(request, response);
//			return;
//		}
		
		if(token == null || !token.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		// JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
		//String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
		String jwtToken = token.replace("Bearer ", "");
		if(JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
		.build().verify(jwtToken).getExpiresAt().before(Calendar.getInstance().getTime())) {
			chain.doFilter(request, response);
			throw new RuntimeException("Exired token~!");
		}else {
			String userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
					.build().verify(jwtToken)
					.getClaim("id").asString();
			System.out.println("userId : " + userId);
			// 서명이 정상적으로 됨
			if(userId != null) {
				log.info("username 정상");
				UserDto userEntity = new UserDto();
				// select workspace 
				if(!ws_id.equals("0")) {
					log.info("select workspace");
					userEntity = userDAO.findByWsUsername(userId,ws_id);
				}else if(ws_id.equals("0")){
					log.info("not select workspace");
					// not select workspace
					userEntity = userDAO.findByUsername(userId);
					log.info("[userEntity] "+userEntity);
					if(userEntity.getRole_type() == null || userEntity.getRole_type().equals("")) {
						log.info("NULL ROLETYPE");
						userEntity.setRole_type("ROLE_USER");					
					}
				}
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
				chain.doFilter(requestWrapper, response);
			}
		}
		chain.doFilter(requestWrapper, response);
	}	

//	private Authentication getAuthentication(HttpServletRequest request) {
//		  if (token == null) {
//		    return null;
//		  }
//
//		  Claims claims;
//
//		  try {
//		    claims = jwtUtil.getClaims(token.substring("Bearer ".length()));
//		  } catch (JwtException e) {
//		    throw new AuthException.MalformedJwt(token);
//		  }
//
//		  Set<GrantedAuthority> roles = new HashSet<>();
//		  String role = (String) claims.get("role");
//		  roles.add(new SimpleGrantedAuthority("ROLE_" + role));
//
//		  return new UsernamePasswordAuthenticationToken(new User(claims), null, roles);
//	}
	
}
