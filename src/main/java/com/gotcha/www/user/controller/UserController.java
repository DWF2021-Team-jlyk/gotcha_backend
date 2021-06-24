package com.gotcha.www.user.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.user.service.UserService;
import com.gotcha.www.user.vo.NotiJoinVO;
import com.gotcha.www.user.vo.UserVO;
import com.gotcha.www.user.vo.WorkspaceDto;

@RestController
@RequestMapping("/main")
public class UserController {
	@Autowired
	UserService userService;
	
	HttpSession session;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	// select workspace List
	@PostMapping("/wsList")
	public @ResponseBody List<WorkspaceDto> selectWorkspace(@RequestBody UserVO userVO)
			throws Exception {
		List<WorkspaceDto> mainList = userService.selectWorkspace(userVO.getUser_id());
		System.out.println(mainList);
		return mainList;
	}
	
	// select notice List
	@PostMapping("/notiList")
	public @ResponseBody List<NotiJoinVO> selectNotice(@RequestBody UserVO userVO)
			throws Exception {
		List<NotiJoinVO> mainList = userService.selectNotice(userVO.getUser_id());
		System.out.println(mainList);
		return mainList;
	}
	
	// 회원가입
	@PostMapping("/joinCheck")
	public boolean join(@RequestBody UserVO userVO, HttpServletRequest request) {
		log.info("user_id:" + userVO.toString());
		
		boolean status = userService.checkId(userVO.getUser_id());
		
		if(status == true) {
			log.info("joinCheck true");
			String toMail = userVO.getUser_id();
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
