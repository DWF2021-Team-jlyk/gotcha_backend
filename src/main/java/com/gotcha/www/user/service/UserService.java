package com.gotcha.www.user.service;


import com.gotcha.www.home.vo.UserVO;

public interface UserService {

	// 이메일 보내기
	String sendToEmail(String division, String user_id);

	// 생성 코드 입력 코드 비교
	boolean checkCode(String inputCode, String joinCode);

	// id 중복 검사
	boolean checkId(String user_id);

	// 회원가입
	void insertUser(UserVO userVO);

	// id 활성화
	void updateEnabled(String user_id);

	// get user info
	UserVO getUserInfo(String userId);

	// update userName
	void updateUserName( String user_id,String user_name);

	// check password
	boolean checkPwd(String user_id, String user_pwd);

	// change password
	void changePwd(String user_id, String user_pwd);

	// withdrawal(회원탈퇴)
	boolean withdrawal(String userId);

}
