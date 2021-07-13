package com.gotcha.www.user.vo;

import java.util.HashMap;

import com.gotcha.www.workList.vo.ListVO;
import lombok.Data;

@Data
public class AthorizationVO {
	private String user_id;
	private String user_name;
	private String user_pwd;
	private int ws_id;
	private String ws_name;
	private int list_id;
	private String list_name;
	private int card_id;
	private char is_fav;
	private int role_id;
	private String role_type;
	private ListVO listVO;
}
