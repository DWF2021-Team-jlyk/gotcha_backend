package com.gotcha.www.card.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardServiceTests {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private CardService cardService;

    @Test
    public void getCardsTest(){
        cardService.getCards(1).forEach(log::info);
    }

    @Test
    public void getCardTest(){
        log.info(cardService.getCard(1));
    }
}
