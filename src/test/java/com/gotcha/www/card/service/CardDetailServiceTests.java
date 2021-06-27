package com.gotcha.www.card.service;

import com.gotcha.www.card.vo.CardDetailVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CardDetailServiceTests {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private CardDetailService service;

    @Test
    public void getCardInfoTest(){
        CardDetailVO cardDetail = service.getCardInfo(1);
        log.info("\n"+cardDetail.getCardDTO());
        log.info("\n"+cardDetail.getCardActs());
        log.info("\n"+cardDetail.getCardFiles());
        log.info("\n"+cardDetail.getCardTodos());
        log.info("\n"+cardDetail.getCardMembers());
    }
}
