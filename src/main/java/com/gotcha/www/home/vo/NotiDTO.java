package com.gotcha.www.home.vo;

import lombok.Data;

import java.util.List;

@Data
public class NotiDTO {
    int ws_id;
    String ws_name;
    int noti_type_id;
    char noti_type;
    List<String> user_id;
}
