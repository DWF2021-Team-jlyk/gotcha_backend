package com.gotcha.www.card.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.card.dao.CardTodoDAO;
import com.gotcha.www.card.vo.CardTodoDTO;
import com.gotcha.www.workList.dao.WorkListDAO;

@Service
@Transactional
public class CardTodoServiceImpl implements CardTodoService {

	private final Log log = LogFactory.getLog(this.getClass());

	private final WorkListDAO workListDAO;
	private final CardTodoDAO cardTodoDAO;

	@Autowired
	public CardTodoServiceImpl(WorkListDAO workListDAO, CardTodoDAO cardTodoDAO) {
		this.workListDAO = workListDAO;
		this.cardTodoDAO = cardTodoDAO;
	}

	@Override
	public List<CardTodoDTO> getCardTodo(int card_id) {
		return cardTodoDAO.selectCardTodo(card_id);
	}

	@Override
	public int selectTodoId() {
		return cardTodoDAO.selectTodoId();
	}

	@Override
	public void deleteCardTodo(int todo_id) {
		cardTodoDAO.deleteCardTodo(todo_id);
	}

	@Override
	public void updateCardTodo(CardTodoDTO cardTodo) {
		cardTodoDAO.updateCardTodo(cardTodo);
		
	}

	@Override
	@Transactional
	public void insertCardTodo(CardTodoDTO cardTodo) {
		cardTodo.setTodo_id(cardTodoDAO.selectTodoId());
		log.info("CardTodoServiceImpl"+cardTodo.getTodo_id());
		cardTodoDAO.insertCardTodo(cardTodo);
	}

}
