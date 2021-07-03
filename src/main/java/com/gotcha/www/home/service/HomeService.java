package com.gotcha.www.home.service;

import java.util.List;

import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;

public interface HomeService {
	List<WorkspaceDto> selectWorkspace(String user_id);
	
	List<NotiJoinVO> selectNotice(String user_id);
	
	//fav 바꾸기
	void updateFav(WorkspaceDto workspaceDto);
	
	//workspace setting userlist받아오기
	List<String> selectWsUserList(int ws_id);
	
}
