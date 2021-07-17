package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardMemberDTO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMemberDAO {
    int MemberCount(int card_id);
    List<CardMemberDTO> selectCardMember(int card_id);
    void deleteCardMember(CardMemberDTO cardMemberDTO);
    void deleteCard(int card_id);
    void updateCardMember(CardMemberDTO cardMemberDTO);
    void insertCardMember(CardMemberDTO CardMemberDTO);
}
