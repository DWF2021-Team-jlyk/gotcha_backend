package com.gotcha.www.card.vo;

import lombok.Data;

@Data
public class CardTodoDTO {
    int todo_id;
    String todo_name;
    int card_id;
    String todo_start_date;
    String todo_end_date;
    int todo_isdone;
}
