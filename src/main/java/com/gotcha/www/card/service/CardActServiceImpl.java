package com.gotcha.www.card.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.card.dao.CardActDAO;
import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.workList.dao.WorkListDAO;

@Service
@Transactional
public class CardActServiceImpl implements CardActService {
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	private final WorkListDAO workListDAO;
	private final CardActDAO cardActDAO;
	
	@Autowired
	public CardActServiceImpl(WorkListDAO workListDAO, CardActDAO cardActDAO) {
		this.workListDAO = workListDAO;
		this.cardActDAO = cardActDAO;
	}

	@Override
	public List<CardActDTO> getCardAct(int card_id) {
		List<CardActDTO> cardActList = cardActDAO.selectCardAct(card_id);
		return cardActList;
	
	}

	@Override
	public int selectActId() {
		int act_id =  cardActDAO.selectActId();
		return act_id;
	}

	@Override
	public void insertCardAct(CardActDTO cardActDTO) {
		 cardActDAO.insertCardAct(cardActDTO);
	}


	@Override
	public void deleteCardAct(int act_id) {
		cardActDAO.deleteCardAct(act_id);
		
	}

	@Override
	public void updateCardAct(CardActDTO cardActDTO) {
		cardActDAO.updateCardAct(cardActDTO);
		
	}
	
	@Override
	public CardActDTO getOneCardAct(int card_act_id) {
		return cardActDAO.getOneCardAct(card_act_id);
	}

}
