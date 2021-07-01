package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardMemberDTO;

import java.util.List;

public interface CardMemberDAO {
    int MemberCount(int card_id);
    List<CardMemberDTO> selectCardMember(int user_id);
    void deleteCardMember(String user_id);
    void updateCardMember(CardMemberDTO cardMember);
    void insertCardMember(CardMemberDTO cardMember);
}
