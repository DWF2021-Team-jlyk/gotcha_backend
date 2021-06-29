package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class CardDAOTests {

    private Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private CardDAO cardDAO;

    @Test
    public void selectCardTest() {
        CardDTO cardDTO =
                CardDTO.builder()
                        .card_id(1)
                        .card_name("card1")
                        .card_desc("something")
                        .card_start_date("2021-06-18 14:15:08")
                        .card_end_date("2021-06-19 14:15:08")
                        .ws_id(1)
                        .list_id(1)
                        .card_isdone(0)
                        .build();

        CardDTO storedCardDTO = cardDAO.selectCard(1);
        Assertions.assertEquals(cardDTO, storedCardDTO);
        log.info(storedCardDTO.toString());
    }

    @Test
    public void selectCardsTest() {
        cardDAO.selectCards(1)
                .forEach(log::info);
    }
}
