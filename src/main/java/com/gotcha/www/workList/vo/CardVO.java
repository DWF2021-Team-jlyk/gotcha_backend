package com.gotcha.www.workList.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardVO {
	private int card_id;
	private String card_name;
	private String card_desc;
	private Date card_start_date;
	private Date card_end_date;
	private int list_id;
	private int ws_id;
}	