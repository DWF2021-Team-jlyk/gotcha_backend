package com.gotcha.www.home.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;

@Mapper
public interface HomeDAO{

	List<WorkspaceDto> selectWorkspace(String user_id);
	
	List<NotiJoinVO> selectNoti(String user_id);
	
	void updateFav(WorkspaceDto workspaceDto);
	
	List<String> selectWsUserList(int ws_id);

	// workspace get last sequence
	int getWsLastIndex();
	
	// add workspace
//	void addWorkspace(HashMap<String, Object> map);
	void createWorkspace(HashMap<String, Object> workspaceMap);
	
	// create user role
	void createUserRole(HashMap<String, Object> map);

}
