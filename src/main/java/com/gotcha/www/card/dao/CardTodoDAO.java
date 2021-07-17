package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardTodoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardTodoDAO {
    int selectTodoId();
    int todoCount(int card_id);
    List<CardTodoDTO> selectCardTodo(int card_id);
    void deleteCardTodo(int todo_id);
    void deleteCard(int card_id);
    void updateCardTodo(CardTodoDTO cardTodo);
    void insertCardTodo(CardTodoDTO cardTodo);
    void updateTodoIsDone(CardTodoDTO cardTodoDTO);
}
