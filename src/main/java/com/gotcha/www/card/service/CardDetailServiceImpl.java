package com.gotcha.www.card.service;

import com.gotcha.www.card.dao.CardDAO;
import com.gotcha.www.card.dao.CardDetailDAO;
import com.gotcha.www.card.dao.CardTodoDAO;
import com.gotcha.www.card.vo.CardDTO;
import com.gotcha.www.card.vo.CardDetailVO;
import com.gotcha.www.card.vo.CardTodoDTO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
@Transactional
public class CardDetailServiceImpl implements CardDetailService{

    private final Log log = LogFactory.getLog(this.getClass());

    private final CardDAO cardDAO;
    private final CardDetailDAO cardDetailDAO;
    private final CardTodoDAO cardTodoDAO;

    @Autowired
    public CardDetailServiceImpl(CardDAO cardDAO, CardDetailDAO cardDetailDAO, CardTodoDAO cardTodoDAO) {
        this.cardDAO = cardDAO;
        this.cardDetailDAO = cardDetailDAO;
        this.cardTodoDAO = cardTodoDAO;
    }

    public CardDetailVO getCardInfo(int card_id){
        CardDetailVO cardDetailVO = CardDetailVO.builder()
                .cardDTO(cardDAO.selectCard(card_id))
                .cardMembers(cardDetailDAO.selectMember(card_id))
                .cardTodos(cardDetailDAO.selectTodoList(card_id))
                .cardFiles(cardDetailDAO.selectFile(card_id))
                .cardActs(cardDetailDAO.selectCardAct(card_id))
                .build();

        System.out.println(cardDetailVO.toString());
        return cardDetailVO;
    }

	@Override
	public void updateTodoIsDone(CardTodoDTO cardTodoDTO) {
		// TODO Auto-generated method stub
		cardTodoDAO.updateTodoIsDone(cardTodoDTO);
	}



}
