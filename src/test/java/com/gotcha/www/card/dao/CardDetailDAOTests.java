package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardTodoDTO;
import lombok.extern.log4j.Log4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CardDetailDAOTests {

    private Log log = LogFactory.getLog(this.getClass());

//    @Autowired
//    private CardDetailDAO cardDetailDAO;
//
//    @Test
//    public void selectCardTodoTest() {
//        List<CardTodoDTO> todoList = cardDetailDAO.selectTodoList(1);
//        Assertions.assertNotNull(todoList);
//        log.info("\n"+todoList);
//    }

    @Test
    public void selectCardActTest() {

    }
}
