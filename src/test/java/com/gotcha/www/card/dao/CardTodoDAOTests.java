package com.gotcha.www.card.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CardTodoDAOTests {

    @Autowired
    private CardTodoDAO cardTodoDAO;

    private Log log = LogFactory.getLog(this.getClass());

    @Test
    public void selectCardTodoTest(){
        cardTodoDAO.selectCardTodo(1).forEach(log::info);
    }
}
