package com.gotcha.www.workList.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ListVO {
	private int ws_id;
	private int list_id;
	private String list_name;
	
	
	//private List<CardVO> card;
	
}
