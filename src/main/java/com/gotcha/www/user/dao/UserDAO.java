package com.gotcha.www.user.dao;

import com.gotcha.www.home.vo.WorkspaceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.user.vo.UserDto;
import com.gotcha.www.user.vo.UserVO;

import java.util.List;

@Mapper
public interface UserDAO {

	// get login info
	public UserDto findByUsername(String username);
	
	// get workspace authorization
	public UserDto findByWsUsername(@Param("user_id") String username,@Param("ws_id") String ws_id);
	
	// admin workspaceList 
	public List<WorkspaceDto> selectWorkspace(String user_id);
	
	// notice workspaceList
	public List<NotiJoinVO> selectNoti(String user_id);

	// id 중복 체크
	public int checkId(String user_id);
	
	// insert join user
	public void insertUser(UserVO userVO);

	// id 활성화
	public void updateEnabled(String user_id);

	// last login time update
	public void updateLastLogin(String username);
	
}
