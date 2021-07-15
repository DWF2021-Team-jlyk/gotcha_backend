package com.gotcha.www.home.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gotcha.www.home.service.HomeService;
import com.gotcha.www.home.vo.InviteMemberVO;
import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.UserVO;
import com.gotcha.www.home.vo.WorkspaceDto;
import com.gotcha.www.user.service.UserService;
import com.gotcha.www.user.vo.PrincipalDetails;


@RequestMapping("/home")
@RestController
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
	public @ResponseBody List<String> selecWsUserList(@RequestBody HashMap<String, Integer> map,
			@AuthenticationPrincipal PrincipalDetails principalDetails)
			throws Exception {
		userId = getLoginUser(principalDetails);
		int ws_id = map.get("ws_id");
		log.info("[USERLIST] " + ws_id);
		List<String> wsUserList = homeService.selectWsUserList(ws_id);
		log.info("[USERLISTS] " + wsUserList);
		return wsUserList;
	}
    
	@PostMapping("/getFileName")
	public String getFileName(@RequestBody HashMap<String, Integer> map) {
		log.info("[REQUEST FILE NAME]");
		int ws_id = map.get("ws_id");
		log.info("[WS_ID] " + ws_id);
		String ws_isImage = homeService.getFileName(ws_id);
		return ws_isImage;
	}
	
	@PostMapping("/updateWsName")
	public void updateWsName(@RequestBody WorkspaceDto workspaceDto) {
		log.info("[UPDATE WORKSPACE NAME] " + workspaceDto);
		homeService.updateWsName(workspaceDto);
	}
	
	// 멤버 추가
	@PostMapping("/inviteMember")
	public void inviteMember(@RequestBody InviteMemberVO inviteMemberVO ) {
		log.info("[email] " + inviteMemberVO);
		homeService.addMember(inviteMemberVO);
	}
	
	@PostMapping("/deleteMember")
	public void deleteMember(@RequestBody HashMap<String, Object> map) {
		log.info("[DELETE MEMBER] ");
		homeService.deleteMember(map);
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
    
    @PostMapping("/updateImg")
    public void updateImg(@RequestParam("ws_id") int ws_id,
    		@RequestParam(required = false, value = "ws_isImage") MultipartFile file, 
    		MultipartHttpServletRequest req) throws IOException {
		
    	boolean fileUpload = false;
    	if(req.getFile("ws_isImage") != null) {
    		
    		log.info("[WS_ID] " + ws_id);
        	log.info("[FILE] " + file);
        	String fileName = file.getOriginalFilename();
        	log.info("[FILE NAME] " + fileName);
    		log.info("[WS_ID] " + ws_id);
    		
    		log.info("[FILE NAME] " + fileName);
    		
    		byte[] fileByte = file.getBytes();
    		fileUpload = homeService.fileUpdate(ws_id, fileName,fileByte);
    		if(fileUpload) {
    			homeService.updateFileName(ws_id, fileName);
    		}
    	}
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
    
    @PostMapping("/addWorkspace")
    public boolean addWorkspace(@RequestParam("ws_name") String ws_name,
							@RequestParam(required = false, value = "ws_isImage") MultipartFile file,
							@RequestParam(required = false, value = "member") List<String> member_id,
							@AuthenticationPrincipal PrincipalDetails principalDetails,
							MultipartHttpServletRequest req,
							HttpServletRequest request) throws IOException {
    	
    	boolean fileUpload = false;
    	userId = getLoginUser(principalDetails);
    	log.info("req.getFile('ws_isImage') :"+req.getFile("ws_isImage"));
    	log.info("[MEMBER] " + member_id);
    	if(req.getFile("ws_isImage") != null) {
			log.info("[REQUEST ADD WORKSPACE]");
			log.info("[FILE] "+file);
			String fileName = file.getOriginalFilename();
			log.info("[fileName] " + file.getOriginalFilename());
			byte[] fileByte = file.getBytes();
			
			log.info("[userId] "+userId);
			
			fileUpload = homeService.fileUpload(ws_name, fileName ,fileByte);
			log.info("[FILE UPLOAD STATE] "+fileUpload);

			if(fileUpload == true) {
				homeService.createWorkspace(userId,ws_name, file.getOriginalFilename(),member_id);
			}
			
			log.info("[FILE UPLOAD] " + fileUpload);
		}else {
			
			log.info("[NULL]");
			homeService.createWorkspace(userId,ws_name, null, member_id);
		}
    	
    	log.info("[UPLOAD FILE] " + file);
//    	homeService.store(file);
    	return fileUpload;
	}

    @PostMapping("/leaveWorkspace")
    public void leaveWorkspace(@RequestBody HashMap<String,Object> map,
    		@AuthenticationPrincipal PrincipalDetails principalDetails) {
    	userId = getLoginUser(principalDetails);
    	int ws_id = (int) map.get("ws_id");
    	String user_id = (String) map.get("user_id");
    	log.info("[LEAVE WORKSPACE]" + ws_id + user_id + userId);
    	homeService.leaveWorkspace(ws_id, user_id, userId);
    }
    
    @PostMapping("/deleteWorkspace")
    public void deleteWorkspace(@RequestBody HashMap<String,Object> map,
    		@AuthenticationPrincipal PrincipalDetails principalDetails) throws IOException {
    	userId = getLoginUser(principalDetails);
    	int ws_id = (int) map.get("ws_id");
    	log.info("[DELETE CONTROLLER REQUEST] " + ws_id);
    	homeService.deleteWorkspace(ws_id, userId);
    }
    
    @PostMapping("/getAllUsers")
    public @ResponseBody List<String> selectUserList(@RequestBody InviteMemberVO inviteMemberVO,
    		@AuthenticationPrincipal PrincipalDetails principalDetails){
    	userId = getLoginUser(principalDetails);
    	log.info("[MEMBER LIST] " + inviteMemberVO);
    	inviteMemberVO.setUser_id(userId);
        return homeService.getAllUserId(inviteMemberVO);
    }
    
    // get login id
    public String getLoginUser(PrincipalDetails principalDetails) {
    	String userId="";
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PrincipalDetails) {
			userId = ((PrincipalDetails) principal).getUsername();
		} else {
			userId = principal.toString();
		}
		return userId;
    }

}
