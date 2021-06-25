package com.gotcha.www.card.vo;

import lombok.Data;

@Data
public class CardActDTO {
    int card_id;
    int act_id;
    String user_id;
    String act_desc;
    String created_date;
    String islog;
}
