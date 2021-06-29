package com.gotcha.www.card.service;

import com.gotcha.www.card.vo.CardDTO;

import java.util.List;

public interface CardService {

    List<CardDTO> getCards(int wsId);

    CardDTO getCard(int cardId);
}
