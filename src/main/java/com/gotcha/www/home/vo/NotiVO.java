package com.gotcha.www.home.vo;

import lombok.Data;

@Data
public class NotiVO {
    int ws_id;
    int noti_id;
    int noti_type_id;
    String noti_time;
    String noti_desc;
    char noti_type;
    char noti_checked;
}
