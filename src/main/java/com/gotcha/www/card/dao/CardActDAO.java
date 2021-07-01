package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardActDTO;

import java.util.List;

public interface CardActDAO {
    int selectActId();
    List<CardActDTO> selectCardAct(int card_id);
    void deleteCardAct(int act_id);
    void updateCardAct(CardActDTO cardAct);
    void insertCardAct(CardActDTO cardAct);
}
