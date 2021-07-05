package com.gotcha.www.card.service;

import java.util.List;

import com.gotcha.www.card.vo.CardActDTO;

public interface CardActService {
	List<CardActDTO> getCardAct(int card_id);
}
