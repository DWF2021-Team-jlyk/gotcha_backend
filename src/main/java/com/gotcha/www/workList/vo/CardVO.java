package com.gotcha.www.workList.vo;

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
	private int list_id;
	//private int ws_id;
}	
