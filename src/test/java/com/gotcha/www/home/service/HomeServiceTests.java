package com.gotcha.www.home.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HomeServiceTests {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private HomeService service;

    @Test
    public void selectNoticeTest(){
        service.selectNotice("user01@naver.com")
                .forEach(log::info);
    }

}
