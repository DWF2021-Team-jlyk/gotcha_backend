package com.gotcha.www.board.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	//private int board_id;
	private int id;
	private String board_title;
	private String user_id;
	private String board_reg_time;
	private String board_mod_time;
	private int ws_id;
	private String board_content;
}
