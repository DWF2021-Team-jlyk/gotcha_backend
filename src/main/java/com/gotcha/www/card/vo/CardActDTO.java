package com.gotcha.www.card.vo;

import lombok.Data;

@Data
public class CardActDTO {
    private int card_id;
    private int act_id;
    private String user_id;
    private String act_desc;
    private String created_date;
    private String islog;
}
