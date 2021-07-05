package com.gotcha.www.card.service;

import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.card.vo.CardFileDTO;
import com.gotcha.www.card.vo.CardMemberDTO;
import com.gotcha.www.card.vo.CardTodoDTO;

import java.util.List;
import java.util.Optional;


public interface CardDetailService {
    List<CardActDTO> getCardAct(int card_id);
    List<CardMemberDTO> getCardMem(int card_id);
    List<CardFileDTO> getCardFile(int card_id);
    List<CardTodoDTO> getCardTodo(int card_id);
}
