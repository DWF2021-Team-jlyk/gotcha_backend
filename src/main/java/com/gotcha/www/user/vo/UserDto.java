package com.gotcha.www.user.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
	private String ws_id;
	private String user_id;
	private String user_pwd;
	private String user_name;
	private String user_enabled;
	private String role_type;
}
