package com.gotcha.www.card.service;

import java.util.List;

import com.gotcha.www.card.vo.CardFileDTO;

public interface CardFileService {
	List<CardFileDTO> getCardFile(int card_id);
}
