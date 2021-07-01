package com.gotcha.www.card.service;

import com.gotcha.www.card.dao.CardDetailDAO;
import com.gotcha.www.card.vo.CardDetailVO;
import com.gotcha.www.workList.dao.WorkListDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class CardDetailServiceImpl implements CardDetailService{

    private final Log log = LogFactory.getLog(this.getClass());

    private final WorkListDAO workListDAO;
    private final CardDetailDAO cardDetailDAO;

    @Autowired
    public CardDetailServiceImpl(WorkListDAO workListDAO, CardDetailDAO cardDetailDAO) {
        this.workListDAO = workListDAO;
        this.cardDetailDAO = cardDetailDAO;
    }

    public CardDetailVO getCardInfo(int card_id){
        CardDetailVO cardDetailVO = CardDetailVO.builder()
                .cardVO(workListDAO.selectOneCard(card_id))
                .cardMembers(cardDetailDAO.selectMember(card_id))
                .cardTodos(cardDetailDAO.selectTodoList(card_id))
                .cardFiles(cardDetailDAO.selectFile(card_id))
                .cardActs(cardDetailDAO.selectCardAct(card_id))
                .build();

        return cardDetailVO;
    }



}
