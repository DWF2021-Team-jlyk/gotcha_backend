package com.gotcha.www.card.service;

import java.util.List;

import com.gotcha.www.card.vo.CardTodoDTO;

public interface CardTodoService {
	List<CardTodoDTO> getCardTodo(int card_id);
}
