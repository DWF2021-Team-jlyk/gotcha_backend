package com.gotcha.www.home.vo;

import lombok.Data;

@Data
public class UserVO {
	private String user_id;
	private String user_pwd;
	private String user_enabled;
	private String user_name;
	private String user_last_login;
}	
