package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardDAO {
    //worklist card
    List<CardDTO> selectCards(int ws_id);

    CardDTO selectCard(int card_id);

    void insertCard(CardDTO cardDTO);

    void deleteCard(int card_id);
}
