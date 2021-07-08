package com.gotcha.www.card.service;

import java.util.List;

import com.gotcha.www.card.vo.CardActDTO;

public interface CardActService {
	List<CardActDTO> getCardAct(int card_id);
	int selectActId();
	void insertCardAct(CardActDTO cardActDTO);
	void deleteCardAct(int act_id);
	void updateCardAct(CardActDTO cardActDTO);
	CardActDTO getOneCardAct(int card_act_id);
}
