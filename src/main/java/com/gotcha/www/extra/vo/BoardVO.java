package com.gotcha.www.extra.vo;

import lombok.Data;

@Data
public class BoardVO {
    int id;
    String board_title;
    String user_id;
    int ws_id;
    String board_content;
    String board_reg_time;
    String board_mod_time;
}
