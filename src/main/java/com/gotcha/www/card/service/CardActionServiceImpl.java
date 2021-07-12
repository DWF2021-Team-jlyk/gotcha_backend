package com.gotcha.www.card.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.card.dao.CardActDAO;
import com.gotcha.www.card.dao.CardActionDAO;
import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.workList.dao.WorkListDAO;
import com.gotcha.www.workList.vo.CardVO;

@Service
@Transactional
public class CardActionServiceImpl implements CardActionService {

	private final CardActionDAO cardActionDAO;

	@Autowired
	public CardActionServiceImpl(CardActionDAO cardActionDAO) {
		this.cardActionDAO = cardActionDAO;
	}
	
	@Override
	public int getMaxPosition(CardVO cardVO) {
		int MaxPosition = cardActionDAO.getMaxPosition(cardVO);
		return MaxPosition;
	}

	@Override
	public void updateCardMove(CardVO cardVO) {
		cardActionDAO.updateCardMove(cardVO);
	}

	@Override
	public void updateDestPosition(CardVO cardVO) {
		cardActionDAO.updateDestPosition(cardVO);
		
	}

	@Override
	public void updateNowPosition(CardVO cardVO) {
		cardActionDAO.updateNowPosition(cardVO);

	}
	
}
