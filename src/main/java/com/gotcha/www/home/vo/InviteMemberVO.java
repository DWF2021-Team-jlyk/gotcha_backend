package com.gotcha.www.home.vo;

import java.util.List;

import lombok.Data;

@Data
public class InviteMemberVO {
	private int ws_id;
	private String user_id;
	private List<String> emailList;
}
