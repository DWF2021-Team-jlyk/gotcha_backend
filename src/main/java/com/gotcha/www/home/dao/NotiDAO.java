package com.gotcha.www.home.dao;

import com.gotcha.www.home.vo.NotiUserVO;
import com.gotcha.www.home.vo.NotiVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Transactional
public interface NotiDAO {
    int getNotiId();

    List<NotiVO> getNotiList();

    void insertNoti(NotiVO noti);

    void insertNotiUser(NotiUserVO vo);

    void changeNotiCheck(int noti_id);

    void deleteNoti(int noti_id);

    List<String> getWsMember(int ws_id);
}
