package com.gotcha.www.card.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.card.dao.CardMemberDAO;
import com.gotcha.www.card.vo.CardMemberDTO;
import com.gotcha.www.workList.dao.WorkListDAO;

@Service
@Transactional
public class CardMemberServiceImpl implements CardMemberService {
	
	private final Log log = LogFactory.getLog(this.getClass());

	private final WorkListDAO workListDAO;
	private final CardMemberDAO cardMemberDAO;

	@Autowired
	public CardMemberServiceImpl(WorkListDAO workListDAO, CardMemberDAO cardMemberDAO) {
		this.workListDAO = workListDAO;
		this.cardMemberDAO = cardMemberDAO;
	}

	@Override
	public List<CardMemberDTO> getCardMem(int card_id) {
		List<CardMemberDTO> cardMemberList = cardMemberDAO.selectCardMember(card_id);
		System.out.println(cardMemberList);
		return cardMemberList;
	}

	@Override
	public void insertCardMember(CardMemberDTO cardMemberDTO) {
		cardMemberDAO.insertCardMember(cardMemberDTO);
	}

}
