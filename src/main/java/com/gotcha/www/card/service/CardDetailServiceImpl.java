package com.gotcha.www.card.service;

import com.gotcha.www.card.dao.CardDetailDAO;
import com.gotcha.www.card.dao.CardTodoDAO;
import com.gotcha.www.card.vo.*;
import com.gotcha.www.workList.dao.WorkListDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CardDetailServiceImpl implements CardDetailService{

    private final Log log = LogFactory.getLog(this.getClass());

    private final WorkListDAO workListDAO;
    private final CardDetailDAO cardDetailDAO;
    private final CardTodoDAO cardTodoDAO;

    @Autowired
    public CardDetailServiceImpl(WorkListDAO workListDAO, CardDetailDAO cardDetailDAO, CardTodoDAO cardTodoDAO) {
        this.workListDAO = workListDAO;
        this.cardDetailDAO = cardDetailDAO;
        this.cardTodoDAO = cardTodoDAO;
    }

    @Override
    public List<CardActDTO> getCardAct(int card_id) {
        return cardDetailDAO.selectCardAct(card_id);
    }

    @Override
    public List<CardMemberDTO> getCardMem(int card_id) {
        return cardDetailDAO.selectMember(card_id);
    }

    @Override
    public List<CardFileDTO> getCardFile(int card_id) {
        return cardDetailDAO.selectFile(card_id);
    }

    @Override
    public List<CardTodoDTO> getCardTodo(int card_id) {
        return cardDetailDAO.selectTodoList(card_id);
    }


}
