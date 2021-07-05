package com.gotcha.www.card.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.card.dao.CardFileDAO;
import com.gotcha.www.card.vo.CardFileDTO;
import com.gotcha.www.workList.dao.WorkListDAO;

@Service
@Transactional
public class CardFileServiceImpl implements CardFileService {

	private final Log log = LogFactory.getLog(this.getClass());

	private final WorkListDAO workListDAO;
	private final CardFileDAO cardFileDAO;

	@Autowired
	public CardFileServiceImpl(WorkListDAO workListDAO, CardFileDAO cardFileDAO) {
		this.workListDAO = workListDAO;
		this.cardFileDAO = cardFileDAO;
	}

	@Override
	public List<CardFileDTO> getCardFile(int card_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
