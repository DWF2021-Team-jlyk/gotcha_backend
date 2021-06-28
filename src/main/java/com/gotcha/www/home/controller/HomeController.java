package com.gotcha.www.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.home.service.HomeService;
import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;
import com.gotcha.www.home.vo.UserVO;


@RestController
@RequestMapping("/home")
public class HomeController {
	@Autowired
	HomeService homeService;
	
	
	@PostMapping("/wsList")
	public @ResponseBody List<WorkspaceDto> selectWorkspace(@RequestBody UserVO userVO)
			throws Exception {
		System.out.println(userVO.toString());
		List<WorkspaceDto> mainList = homeService.selectWorkspace(userVO.getUser_id());
		System.out.println(mainList);
		return mainList;
	}
	
	@PostMapping("/notiList")
	public @ResponseBody List<NotiJoinVO> selectNotice(@RequestBody UserVO userVO)
			throws Exception {
		List<NotiJoinVO> mainList = homeService.selectNotice(userVO.getUser_id());
		System.out.println(mainList);
		return mainList;
	}
	
	@PostMapping("/favUpdate")
	public @ResponseBody void UpdateFav(@RequestBody WorkspaceDto workspaceDto)
			throws Exception {
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
