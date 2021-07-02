package com.gotcha.www.card.service;

import com.gotcha.www.card.vo.CardDetailVO;
import com.gotcha.www.card.vo.CardTodoDTO;


public interface CardDetailService {
    CardDetailVO getCardInfo(int card_id);
    void updateTodoIsDone(CardTodoDTO cardTodoDTO);
}
