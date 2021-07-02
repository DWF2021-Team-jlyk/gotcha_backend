package com.gotcha.www.user.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.user.service.UserService;
import com.gotcha.www.user.vo.PrincipalDetails;
import com.gotcha.www.home.vo.UserVO;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	HttpSession session;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
    
	@PostMapping("loginPage")
	public void loginPage(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("loginPage");
	}
	
	// 회원가입
	@PostMapping("/joinCheck")
	public boolean join(@RequestBody UserVO userVO, HttpServletRequest request) {
		log.info("user_id:" + userVO.toString());

		boolean status = userService.checkId(userVO.getUser_id());

		if (status == true) {
			log.info("joinCheck true");
			String toMailId = userVO.getUser_id();

			userService.insertUser(userVO);
			String code = userService.sendToEmail("join", toMailId);
			session = request.getSession();
			session.setAttribute("joinCode", code);
			return true;
		} else {
			log.info("joinCheck false");
			return false;
		}

	}
	
	// join code send to email
	@PostMapping("/code")
	public boolean code(@RequestBody HashMap<String,String> map) {
		 String inputCode = map.get("code");
		 String user_id = map.get("user_id");
		 log.info("user_id: "+ user_id);
		 
		 String joinCode = (String) session.getAttribute("joinCode");
		 
		 log.info("getCode: " + session.getAttribute("joinCode"));
		 
		 boolean checkCode = userService.checkCode(inputCode, joinCode);
		 
		 if(checkCode == true) {
			 log.info("Session:"+session.getAttribute("joinCode"));
			 userService.updateEnabled(user_id);
			 session.removeAttribute("joinCode");
		 }
		 
		 return checkCode;
	}
	
	@PostMapping("/pwdFind")
	public boolean pwdFind(@RequestBody HashMap<String,String> map) {
		String user_id = map.get("user_id");
		log.info("user_id : "+user_id);
		
		boolean checkCode = userService.checkId(user_id);
		
		if(checkCode == false) {
			userService.sendToEmail("find",user_id);
			return true;
		}
		
		return false;
	}
	
	@PostMapping("/accessDenied")
	public void accessDenied() {
		log.info("[accessDenied....]");
	}

}
