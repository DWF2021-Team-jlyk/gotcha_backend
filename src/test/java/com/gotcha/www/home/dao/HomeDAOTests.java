package com.gotcha.www.home.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.gotcha.www.home.vo.NotiJoinVO;

@SpringBootTest
public class HomeDAOTests <T> {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private HomeDAO homeDAO;

    @Test
    public void selectWorkspaceTest(){
//        homeDAO.selectWorkspace("user01@naver.com").forEach(log::info);
    }

    @Test
    public void selectNotiTest() {
        List<NotiJoinVO> list = homeDAO.selectNoti("user01@naver.com");
        log.info(list);
    }
}
