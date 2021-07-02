package com.gotcha.www.home.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.home.service.HomeService;
import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.UserVO;
import com.gotcha.www.home.vo.WorkspaceDto;
import com.gotcha.www.user.service.UserService;
import com.gotcha.www.user.vo.PrincipalDetails;


@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService homeService;

    @Autowired
    UserService userService;
    
    private Log log = LogFactory.getLog(this.getClass());
    private static String userId;
    
    @PostMapping("/wsList")
	public @ResponseBody List<WorkspaceDto> selectWorkspace(@RequestBody UserVO userVO
			, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		userId = getLoginUser(principalDetails);
		List<WorkspaceDto> mainList = homeService.selectWorkspace(userId);
		log.info("[/wsList RESULT] " + mainList);
		return mainList;
	}
	
	@PostMapping("/notiList")
	public @ResponseBody List<NotiJoinVO> selectNotice(@RequestBody UserVO userVO
			, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		userId = getLoginUser(principalDetails);
		List<NotiJoinVO> mainList = homeService.selectNotice(userId);
		log.info("노티" + mainList);
		return mainList;
	}
	
	@PostMapping("/favUpdate")
	public @ResponseBody void UpdateFav(@RequestBody WorkspaceDto workspaceDto
			, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		userId = getLoginUser(principalDetails);
		workspaceDto.setUser_id(userId);
		homeService.updateFav(workspaceDto);
	}
	
	@PostMapping("/wsUserList")
	public @ResponseBody List<String> selecWsUserList(@RequestBody int ws_id)
			throws Exception {
		List<String> wsUserList = homeService.selectWsUserList(ws_id);
		return wsUserList;
		
	}
    
    @PostMapping("/myPage")
	public UserVO myPage(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		userId = getLoginUser(principalDetails);
		UserVO userVO = userService.getUserInfo(userId);
		log.info("[Request myPage UserVO] " + userVO.toString());
		return userVO;
	}
    
    @PostMapping("/updateUserName")
    public void updateUserName(@RequestBody UserVO userVO,
    		@AuthenticationPrincipal PrincipalDetails principalDetails) {
    	log.info("updateUserName");
    	log.info("user_name"+ userVO.getUser_name());
    	userId = getLoginUser(principalDetails);
    	log.info("[login id] " + userId);
    	userService.updateUserName(userId, userVO.getUser_name());
    }
    
    @PostMapping("/checkCurrentPwd")
    public boolean checkCurrentPwd(@RequestBody UserVO userVO,
    		@AuthenticationPrincipal PrincipalDetails principalDetails) {
    	log.info("[checkCurrentPwd Request] " + userVO.getUser_pwd());
    	userId = getLoginUser(principalDetails);
    	boolean checkPwd = userService.checkPwd(userId, userVO.getUser_pwd());
    	log.info("[CURRENT PASSWORD RESULT] "+checkPwd);
    	return checkPwd;
    }
    
    @PostMapping("/changePwd")
    public void changePwd(@RequestBody UserVO userVO,
    		@AuthenticationPrincipal PrincipalDetails principalDetails) {
    	userId = getLoginUser(principalDetails);
    	log.info("[changPwd userVO]" + userVO);
    	userService.changePwd(userId, userVO.getUser_pwd());
    }
    
    @PostMapping("/withdrawal")
    public boolean withdrawal(@AuthenticationPrincipal PrincipalDetails principalDetails) {
    	userId = getLoginUser(principalDetails);
    	boolean status = userService.withdrawal(userId);
    	return status;
    }
    
    // get login id
    public String getLoginUser(PrincipalDetails principalDetails) {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		if (principal instanceof PrincipalDetails) {
			userId = ((PrincipalDetails) principal).getUsername();
		} else {
			userId = principal.toString();
		}
		return userId;
    }
}
