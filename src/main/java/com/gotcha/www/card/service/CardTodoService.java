package com.gotcha.www.card.service;

import java.util.List;

import com.gotcha.www.card.vo.CardTodoDTO;
import com.gotcha.www.workList.vo.ListVO;

public interface CardTodoService {
	List<CardTodoDTO> getCardTodo(int card_id);
	
	int selectTodoId();

	void deleteCardTodo(int todo_id);
	
    void updateCardTodo(CardTodoDTO cardTodo);
    
    void insertCardTodo(CardTodoDTO cardTodo);
}
