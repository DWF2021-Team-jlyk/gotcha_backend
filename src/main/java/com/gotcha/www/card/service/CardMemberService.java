package com.gotcha.www.card.service;

import java.util.List;

import com.gotcha.www.card.vo.CardMemberDTO;

public interface CardMemberService {
	List<CardMemberDTO> getCardMem(int card_id);
	void insertCardMember(CardMemberDTO cardMemberDTO);
}
