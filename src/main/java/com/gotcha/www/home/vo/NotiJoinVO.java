package com.gotcha.www.home.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NotiJoinVO {
	private int ws_id;
	private String user_id;
	private String ws_name;
	private String noti_type;
	private String noti_desc;
	private Date noti_time;
	
}
