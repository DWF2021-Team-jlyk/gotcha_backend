package com.gotcha.www.user.service;


import java.util.List;

import com.gotcha.www.user.vo.NotiJoinVO;
import com.gotcha.www.user.vo.UserVO;
import com.gotcha.www.user.vo.WorkspaceDto;

public interface UserService {
	
	// select workspace List
	List<WorkspaceDto> selectWorkspace(String user_id);

	// 이메일 보내기
	String sendToEmail(String toMail);

	// 생성 코드 입력 코드 비교
	boolean checkCode(String inputCode, String joinCode);

	// select notice List
	List<NotiJoinVO> selectNotice(String user_id);

	// id 중복 검사
	boolean checkId(String user_id);

	void insertUser(UserVO userVO);

	void updateEnabled(String user_id);

}
