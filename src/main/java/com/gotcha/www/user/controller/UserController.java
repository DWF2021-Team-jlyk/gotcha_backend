package com.gotcha.www.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.gotcha.www.home.service.HomeService;
import com.gotcha.www.home.vo.UserVO;
import com.gotcha.www.user.file.UploadFileUtil;
import com.gotcha.www.user.service.UserService;
import com.gotcha.www.user.vo.PrincipalDetails;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
    @Autowired
    HomeService homeService;
	
	@Autowired
	UploadFileUtil uploadFileUtil;
	
	HttpSession session;
	
	@Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(2000000000);
        return multipartResolver;
    }
	
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
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		log.info("[accessDenied....]");
		return "/Login";
	}

}
