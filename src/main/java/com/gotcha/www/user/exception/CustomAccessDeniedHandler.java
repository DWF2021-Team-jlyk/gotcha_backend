package com.gotcha.www.user.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.gotcha.www.user.vo.PrincipalDetails;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
	
	private static final Logger log = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		try {
			System.out.println("??????????????????????????????????????????");
//		Object principal
//        = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		
//		String userId="";
//		if (principal instanceof PrincipalDetails) {
//			userId = ((PrincipalDetails) principal).getUsername();
//		} else {
//			userId = principal.toString();
//		}
//      if (userId == null) {
//    	  log.warn("User: " + userId 
//            + " attempted to access the protected URL: "
//            + request.getRequestURI());
//      }
		log.info("[ContextPath] "+request.getContextPath());
		log.info("[RequestURI] "+request.getRequestURI());
		log.info("[Denied Get Path] " +  request.getServletPath());
//      request.getRequestDispatcher(this.errorPage).forward(request, response);
		} catch (AccessDeniedException e) {
			response.sendRedirect("/user/accessDenied");
			e.printStackTrace();
		}
      
     
      
		
	}
	
}
