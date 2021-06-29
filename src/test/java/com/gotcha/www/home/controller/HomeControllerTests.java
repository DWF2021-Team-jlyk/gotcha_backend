package com.gotcha.www.home.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest
@WebMvcTest
public class HomeControllerTests {
    @Autowired
    private HomeController controller;
    @Autowired
    private MockMvc mockMvc;
    private Log log = LogFactory.getLog(this.getClass());

    @Test
    public void notiListTest(){
        MultiValueMap<String, String> info = new LinkedMultiValueMap<>();
//
//        info.add("user_id",);
//        info.add("user_pwd",);
//        info.add("user_enable",);
//        info.add("user_name",);
//        info.add("user_last_",);
    }
}
