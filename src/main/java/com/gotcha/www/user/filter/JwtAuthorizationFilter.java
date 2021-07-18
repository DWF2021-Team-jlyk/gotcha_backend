package com.gotcha.www.user.filter;


import java.io.BufferedReader;

/*
 * 작성일시 : 2021-06-21
 * 작성자 : 장승업
 * token의 있는 유저 정보를 토대로
 * 작업하고자 하는 ws_id를 검색하여 권한을 관리
 */

import java.io.IOException;
import java.io.InputStreamReader;
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
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotcha.www.user.config.JwtProperties;
import com.gotcha.www.user.config.RequestBodyWrapper;
import com.gotcha.www.user.dao.UserDAO;
import com.gotcha.www.user.vo.AthorizationVO;
import com.gotcha.www.user.vo.PrincipalDetails;
//import com.gotcha.www.user.filter.AuthException;
//import com.gotcha.www.user.filter.JwtException;
//import com.gotcha.www.user.filter.User;
import com.gotcha.www.user.vo.UserDto;

// 시큐리티가 filter가지고 있는데 그 필터중에 BasicAuthenticationFilter라는 것이 있다.
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 타게 되어있음.
// 만약에 권한이 인증이 필요한 주소가 아니라면 이 필터를 안탄다.
public class JwtAuthorizationFilter extends BasicAuthenticationFilter{
	//public class JwtAuthorizationFilter extends OncePerRequestFilter{
	private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

	private final UserDAO userDAO;

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserDAO userDAO) {
		super(authenticationManager);
		this.userDAO = userDAO;
	}

//	public JwtAuthorizationFilter(UserDAO userDAO) {
//		super();
//		this.userDAO = userDAO;
//	}

	// 인증이나 권한이 필요한 주소 요청이 있을 때 해당 필터를 타게 됨.
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String ws_id = "0", jwtHeader= "", jwtToken = "";
		String requestURL = request.getServletPath();
		log.info("[REQUEST PATH] " + request.getServletPath());

		jwtHeader = request.getHeader(JwtProperties.HEADER_STRING);
		log.info("[jwtHeader] "+jwtHeader);
		jwtToken = jwtHeader.replace("Bearer ", "");
		log.info("[jwtToken] "+jwtToken);
//		jwtToken = token.replace("Bearer ", "");
		
		// header가 있는지 확인
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
//			PrintWriter out = response.getWriter();
//			out.print("<javascript>");
//			out.print("alert('null');");
//			out.print("</javascript>");
			log.info("[TOKEN IS NULL]");
//			AccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
//			accessDeniedHandler.handle(request, response, null);
			chain.doFilter(request, response);
			return;
		}
		
		RequestBodyWrapper requestWrapper = new RequestBodyWrapper(request);
		log.info("[requestWrapper] " + requestWrapper.getInputStream().read());
		log.info("[requestWrapper] " + requestWrapper.getReader());
		log.info("[requestWrapper] " + requestWrapper.getUserPrincipal());
		
		if((requestWrapper.getInputStream().read()) > -1) {

		}
		
		if(requestURL.startsWith("/main/wsList")) {
			ObjectMapper om = new ObjectMapper();
			AthorizationVO authorizationVO = om.readValue(requestWrapper.getInputStream(), AthorizationVO.class);

			log.info("[authorizationVO INFO] " + authorizationVO.toString());
			ws_id = Integer.toString(authorizationVO.getWs_id());
			log.info("[authorizationVO WS_ID] " + authorizationVO.getWs_id());
			log.info("[getWs_name] " + authorizationVO.getWs_name());			
		}
		
		if (JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getExpiresAt()
				.before(Calendar.getInstance().getTime())) {
			chain.doFilter(requestWrapper, response);
			throw new RuntimeException("[Exired token~!]");
		} else {
				
				// Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
				Authentication authentication = checkAuthorization(ws_id, jwtToken);

				// session 공간
				// 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장
				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.info("[JwtAuthorize] 인증이나 권한이 필요한 주소 요청이 됨.");
				chain.doFilter(requestWrapper, response);
		}
}

// JWT 토큰을 검증을 해서 정상적인 사용자인지 확인
private Authentication checkAuthorization(String ws_id, String jwtToken) {
	
	Authentication authentication = null;
	String userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(jwtToken).getClaim("id").asString();
	log.info("[TOKEN LOGIN ID] " + userId);
	// 서명이 정상적으로 됨
	if (userId != null) {
		log.info("username 정상");
		UserDto userEntity = new UserDto();
		// select workspace
		if (!ws_id.equals("0")) {
			log.info("[SELECT WORKSPACE]");
			userEntity = userDAO.findByWsUsername(userId, ws_id);
		} else if (ws_id.equals("0")) {
			log.info("[NOT SELECT WORKSPACE]");
			// not select workspace
			userEntity = userDAO.findByUsername(userId);
			log.info("[USERENTITY] " + userEntity);
			if (userEntity.getRole_type() == null || userEntity.getRole_type().equals("")) {
				log.info("[NULL ROLETYPE]");
				userEntity.setRole_type("ROLE_USER");
			}
			log.info("[USERENTITY GET ID] " + userEntity.getUser_id());
			log.info("[USERENTITY ROLE_TYPE] " + userEntity.getRole_type());
			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			log.info("[PRINCIPALDETAILS GET ID] " + principalDetails.getUsername() + ".....");

			// Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
			authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,
					principalDetails.getAuthorities());

		}
		log.info("[USERENTITY GET ID] " + userEntity.getUser_id());
		log.info("[USERENTITY ROLE_TYPE] " + userEntity.getRole_type());
		PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
		log.info("[PRINCIPALDETAILS GET ID] " + principalDetails.getUsername() + ".....");

		// Jwt 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
		authentication = new UsernamePasswordAuthenticationToken(principalDetails, null,
				principalDetails.getAuthorities());

	}
	return authentication;
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
