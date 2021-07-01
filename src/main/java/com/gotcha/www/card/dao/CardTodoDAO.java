package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardTodoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardTodoDAO {
    List<CardTodoDTO> selectCardTodo(int card_id);
    void deleteCardTodo(int card_id, int todo_id);
    void updateCardTodo(CardTodoDTO cardTodo);
    void insertCardTodo(CardTodoDTO cardTodo);
    void changeTodoIsDone(CardTodoDTO cardTodoDTO);
}
