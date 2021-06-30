package com.gotcha.www.home.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import com.gotcha.www.user.vo.UserDto;


@RestController
@RequestMapping("/home")
public class HomeController {
	
	private final HomeService homeService;
	
	@Autowired
	public HomeController(HomeService homeService) {
		this.homeService = homeService;
	}
	
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@PostMapping("/wsList")
	public @ResponseBody List<WorkspaceDto> selectWorkspace(@RequestBody UserVO userVO) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UserDto userDto = (UserDto)authentication.getPrincipal();
//		System.out.println("[userDto id] " + userDto.getUser_id());
		log.info("\nselectWorkspace: " + userVO);
		List<WorkspaceDto> mainList = homeService.selectWorkspace(userVO.getUser_id());
		System.out.println(mainList);
		return mainList;
	}
	
	@PostMapping("/notiList")
	public @ResponseBody List<NotiJoinVO> selectNotice(@RequestBody UserVO userVO) {
		List<NotiJoinVO> mainList = homeService.selectNotice(userVO.getUser_id());
		System.out.println(mainList);
		return mainList;
	}
	
	@PostMapping("/favUpdate")
	public @ResponseBody void UpdateFav(@RequestBody WorkspaceDto workspaceDto) {
		homeService.updateFav(workspaceDto);
	}
	
	@PostMapping("/wsUserList")
	public @ResponseBody List<String> selecWsUserList(@RequestBody int ws_id)
			throws Exception {
		List<String> wsUserList = homeService.selecWsUserList(ws_id);
		System.out.println(wsUserList);
		return wsUserList;
		
	}
}
