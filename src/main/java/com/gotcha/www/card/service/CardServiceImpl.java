package com.gotcha.www.card.service;

import com.gotcha.www.card.dao.CardDAO;
import com.gotcha.www.card.vo.CardDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService{

    private final Log log = LogFactory.getLog(this.getClass());

    private final CardDAO cardDAO;

    public CardServiceImpl(@Autowired CardDAO cardDAO){
        this.cardDAO = cardDAO;
    }

    @Override
    public List<CardDTO> getCards(int wsId) {
        return cardDAO.selectCards(wsId);
    }

    @Override
    public CardDTO getCard(int cardId) {
        return cardDAO.selectCard(cardId);
    }
}
