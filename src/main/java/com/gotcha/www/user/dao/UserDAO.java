package com.gotcha.www.user.dao;

import com.gotcha.www.home.vo.WorkspaceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.user.vo.UserDto;
import com.gotcha.www.home.vo.UserVO;

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

	// update password
	public void updatePwd(@Param("user_id")String toMail, @Param("user_pwd") String code);

	// get user information
	public UserVO getUserInfo(String userId);

	// update userName
	public void updateUserName(@Param("user_id") String user_id, @Param("user_name") String user_name);

	// check password
	public int checkPwd(@Param("user_id") String user_id, @Param("user_pwd") String user_pwd);

	// get currentPwd
	public UserVO getUserPwd(String user_id);

	// each worksapce admin check
	public int checkWorkspaceAdmin(String userId);
	
	// 댓글 삭제
	public void deleteCardReply(String userId);

	// 카드 멤버 삭제
	public void deleteCardMember(String userId);

	// 유저 알림 삭제
	public void deleteNotiUser(String userId);

	// 가입된 워크스페이스 삭제
	public void deleteUserRole(String userId);

	// 회원 삭제
	public void deleteUser(String userId);
	
}
