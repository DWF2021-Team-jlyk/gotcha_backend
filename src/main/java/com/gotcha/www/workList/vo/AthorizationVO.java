package com.gotcha.www.workList.vo;

import java.util.HashMap;

import lombok.Data;

@Data
public class AthorizationVO {
	private String user_id;
	private String user_name;
	private String user_pwd;
	private int ws_id;
	private String ws_name;
	private int list_id;
	private int card_id;
	private String accessToken;
	private char is_fav;
	private int role_id;
	private String role_type;
	private HashMap<String, Object> formData;
}
