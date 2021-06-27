package com.gotcha.www.user.dao;

import org.apache.ibatis.annotations.Mapper;
import com.gotcha.www.user.vo.UserDto;
import com.gotcha.www.user.vo.UserVO;

@Mapper
public interface UserDAO {
	// login
	public UserDto findByUsername(String username);

	// wsspace
	public UserDto findByWsUsername(String username);

	// id 중복 체크
	public int checkId(String user_id);
	
	// join user
	public void insertUser(UserVO userVO);

	// id 활성화
	public void updateEnabled(String user_id);
}
