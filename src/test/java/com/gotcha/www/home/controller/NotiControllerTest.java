package com.gotcha.www.home.controller;

import com.gotcha.www.home.service.NotiService;
import com.gotcha.www.workList.vo.CardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
@Transactional
class NotiControllerTest {

    @Autowired
    public MockMvc mvc;

    @MockBean
    private NotiService notiService;

    @Test
    void inviteNoti() {
    }

    @Test
    void cardNoti() throws Exception {
        CardVO cardVO = new CardVO();
        cardVO.setCard_id(1);
        cardVO.setWs_id(1);
//        notiService.makeCardNoti(cardVO, "user01@naver.com");

        mvc.perform(
                MockMvcRequestBuilders
                        .post("/noti/cardNoti")
                        .requestAttr("cardVO", cardVO)
                        .requestAttr("userId", "user01@naver.com")
                        .accept(MediaType.APPLICATION_JSON)
                );
    }

    @Test
    void todoNoti() {
    }
}