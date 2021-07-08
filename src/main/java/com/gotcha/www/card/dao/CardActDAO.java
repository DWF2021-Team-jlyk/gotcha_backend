package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardActDTO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CardActDAO {
    int selectActId();
    List<CardActDTO> selectCardAct(int card_id);
    void deleteCardAct(int act_id);
    void updateCardAct(CardActDTO cardAct);
    void insertCardAct(CardActDTO cardAct);
    
    @Select("select * from gc_card_act where act_id = #{act_id}")
    CardActDTO getOneCardAct(int act_id);
}
