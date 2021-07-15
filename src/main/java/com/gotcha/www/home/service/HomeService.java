package com.gotcha.www.home.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gotcha.www.home.vo.InviteMemberVO;
import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;

public interface HomeService {
	List<WorkspaceDto> selectWorkspace(String user_id);
	
	List<NotiJoinVO> selectNotice(String user_id);
	
	//fav 바꾸기
	void updateFav(WorkspaceDto workspaceDto);
	
	//workspace setting userlist받아오기
	List<String> selectWsUserList(int ws_id);
	
	// file upload
	boolean fileUpload(String ws_name, String fileName, byte[] fileByte);
	
	// add workspace
	void createWorkspace(String user_id, String ws_name, String originalFilename, List<String> member_id);
	
	// get filename in workspace
	String getFileName(int ws_id);
	
	// get userList
	List<String> getAllUserId(InviteMemberVO inviteMemberVO);

	// update workspace name
	void updateWsName(WorkspaceDto workspaceDto);

	// update image file
	boolean fileUpdate(int ws_id, String fileName, byte[] fileByte);

	// update file name
	void updateFileName(int ws_id, String fileName);
	
	// 멤버 추가
	void addMember(InviteMemberVO inviteMemberVO);

	// 멤버 제거
	void deleteMember(HashMap<String, Object> map);

	void leaveWorkspace(int ws_id, String user_id, String userId);

	void deleteWorkspace(int ws_id, String userId) throws IOException;
	
	// 파일 업로드
//	void store(MultipartFile file);
	
	// 업로드 폴더 없을 경우 생성
//	void init();
}
