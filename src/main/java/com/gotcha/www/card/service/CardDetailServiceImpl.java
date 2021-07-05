package com.gotcha.www.card.service;

import com.gotcha.www.card.dao.CardDetailDAO;
<<<<<<< HEAD
import com.gotcha.www.card.vo.*;
import com.gotcha.www.workList.dao.WorkListDAO;
=======
import com.gotcha.www.card.dao.CardTodoDAO;
import com.gotcha.www.card.vo.CardDTO;
import com.gotcha.www.card.vo.CardDetailVO;
import com.gotcha.www.card.vo.CardMemberDTO;
import com.gotcha.www.card.vo.CardTodoDTO;

>>>>>>> 62c348b280fa1c257dfed7aedd35adf9f1a30f97
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
<<<<<<< HEAD
    public CardDetailServiceImpl(WorkListDAO workListDAO, CardDetailDAO cardDetailDAO) {
        this.workListDAO = workListDAO;
=======
    public CardDetailServiceImpl(CardDAO cardDAO, CardDetailDAO cardDetailDAO, CardTodoDAO cardTodoDAO) {
        this.cardDAO = cardDAO;
>>>>>>> 62c348b280fa1c257dfed7aedd35adf9f1a30f97
        this.cardDetailDAO = cardDetailDAO;
        this.cardTodoDAO = cardTodoDAO;
    }

<<<<<<< HEAD
 
    @Override
    public List<CardActDTO> getCardAct(int card_id) {
        return cardDetailDAO.selectCardAct(card_id);
    }

    @Override
    public List<CardMemberDTO> getCardMem(int card_id) {
        return cardDetailDAO.selectMember(card_id);
    }
=======
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
		cardTodoDAO.updateTodoIsDone(cardTodoDTO);
	}

	@Override
	public void insertCardMember(CardMemberDTO cardMemberDTO) {
		cardDetailDAO.insertCardMember(cardMemberDTO);
		
	}

>>>>>>> 62c348b280fa1c257dfed7aedd35adf9f1a30f97

    @Override
    public List<CardFileDTO> getCardFile(int card_id) {
        return cardDetailDAO.selectFile(card_id);
    }

    @Override
    public List<CardTodoDTO> getCardTodo(int card_id) {
        return cardDetailDAO.selectTodoList(card_id);
    }
}
