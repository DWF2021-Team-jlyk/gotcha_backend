package com.gotcha.www.workList.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardLogVO {
	private int card_id;
	private String card_name;
	private String card_desc;
	private String card_start_date;
	private String card_end_date;
	private char card_isdone;
	private int list_id;
	private int ws_id;
	private int position;
	
	private String user_id;
}	
