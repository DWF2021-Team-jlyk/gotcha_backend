package com.gotcha.www.user.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.user.service.UserService;
import com.gotcha.www.user.vo.UserVO;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	HttpSession session;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
//	@PostMapping("/loginPage")
//	public String user(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {
//		ObjectMapper om = new ObjectMapper();
//		UserDto userDto = om.readValue(request.getInputStream(), UserDto.class);
//		log.info("login post mapping");
//		log.info("userVO : "+userDto);
//		return "loginPage";
//	}
//	
	// 회원가입
	@PostMapping("/joinCheck")
	public boolean join(@RequestBody UserVO userVO, HttpServletRequest request) {
		log.info("user_id:" + userVO.toString());
		
		boolean status = userService.checkId(userVO.getUser_id());
		
		if(status == true) {
			log.info("joinCheck true");
			String toMail = userVO.getUser_id();
			String rawPassword = userVO.getUser_pwd();
			String encPassword = bCryptPasswordEncoder.encode(rawPassword);
			userVO.setUser_pwd(encPassword);
			System.out.println("pwd : " + userVO.getUser_pwd());
			userService.insertUser(userVO);
			String code = userService.sendToEmail(toMail);
			session = request.getSession();
			session.setAttribute("joinCode", code);
			return true;
		}else {
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
		 
		 log.info("getCode: "+session.getAttribute("joinCode"));
		 
		 boolean checkCode = userService.checkCode(inputCode, joinCode);
		 
		 if(checkCode == true) {
			 System.out.println("Session:"+session.getAttribute("joinCode"));
			 userService.updateEnabled(user_id);
			 session.removeAttribute("joinCode");
		 }
		 
		 return checkCode;
	}
}
