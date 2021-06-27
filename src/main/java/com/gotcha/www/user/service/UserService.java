package com.gotcha.www.user.service;


import com.gotcha.www.user.vo.UserVO;

public interface UserService {

	// 이메일 보내기
	String sendToEmail(String toMail);

	// 생성 코드 입력 코드 비교
	boolean checkCode(String inputCode, String joinCode);

	// id 중복 검사
	boolean checkId(String user_id);

	void insertUser(UserVO userVO);

	void updateEnabled(String user_id);

}
