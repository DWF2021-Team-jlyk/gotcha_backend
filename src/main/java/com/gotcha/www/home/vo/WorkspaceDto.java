package com.gotcha.www.home.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkspaceDto {
	private char is_fav; 
	private int ws_id;
	private String ws_name;
	private String user_id;
	private String user_name;
	private int role_id;
	private String role_type;
}
