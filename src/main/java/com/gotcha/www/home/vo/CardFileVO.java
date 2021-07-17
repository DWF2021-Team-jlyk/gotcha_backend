package com.gotcha.www.home.vo;

import lombok.Data;

@Data
public class CardFileVO {
	private int card_id;
	private int file_id;
	private String file_name;
	private char file_isChecked;
	private String file_path;
}
