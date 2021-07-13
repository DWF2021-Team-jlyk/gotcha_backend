package com.gotcha.www.card.vo;

import lombok.Data;

@Data
public class CardTodoDTO {
    private int todo_id;
    private String todo_name;
    private int card_id;
    private String todo_start_date;
    private String todo_end_date;
    private char todo_isdone;
}
