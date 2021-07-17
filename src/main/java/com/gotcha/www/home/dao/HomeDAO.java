package com.gotcha.www.home.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Param;

import com.gotcha.www.home.vo.CardFileVO;
import com.gotcha.www.home.vo.InviteMemberVO;
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
	
	// workspace get next sequence
	int getWsNextIndex();
	
	// add workspace
	void createWorkspace(HashMap<String, Object> workspaceMap);
	
	// create user role admin
	void setRoleAdmin(HashMap<String, Object> roleAdminMap);

	// create user role member
	void setRoleMember(HashMap<String, Object> roleMemberMap);
	
	// update workspace name
	void updateWsName(WorkspaceDto workspaceDto);
	
	// update file name
	void updateFileName(HashMap<String, Object> map);	
	
	// 멤버 추가
	void addMember(@Param("ws_id") String ws_id, @Param("user_id") String email);
	
	// 멤버 제거
	void deleteMember(HashMap<String, Object> map);
	
	// 이전 파일 이름
	String preFileName(int ws_id);
	
	List<String> getAllUserId(InviteMemberVO inviteMemberVO);

	// admin 위임
	void mandateAdmin(HashMap<String, Object> map);

	// 카드 멤버 삭제
	void deleteCardMember(HashMap<String, Object> deleteMap);
	
	// 가입한 워크스페이스 삭제
	void deleteUserRole(HashMap<String, Object> deleteMap);

	// 즐겨찾기 삭제
	void deleteFav(HashMap<String, Object> deleteMap);

	// card todo 삭제
	void deleteTodos(HashMap<String, Object> deleteMap);

	// card file 삭제
	void deleteFiles(HashMap<String, Object> deleteMap);

	// card 삭제
	void deleteCards(HashMap<String, Object> deleteMap);

	// list 삭제
	void deleteLists(HashMap<String, Object> deleteMap);

	// 게시판 삭제
	void deleteBoard(HashMap<String, Object> deleteMap);

	// 워크스페이스 삭제
	void deleteWorkspace(HashMap<String, Object> deleteMap);

	List<CardFileVO> selectFiles(HashMap<String, Object> deleteMap);


}
