package com.gotcha.www.workList.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.workList.dao.WorkListDAO;
import com.gotcha.www.workList.vo.CardLogVO;
import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;


@Service
public class WorkListServiceImpl implements WorkListService {
	
	@Autowired
	WorkListDAO workListDAO;

	@Override
	public List<ListVO> selectList(int listWsid) {
		return workListDAO.selectList(listWsid);
	}

	@Override
	public int selectListId() {
		return workListDAO.selectListId();
	}

	@Override
	@Transactional
	public void insertList(ListVO listVO) {
		listVO.setList_id(workListDAO.selectListId());
		workListDAO.insertList(listVO);
	}

	@Override
	public void updateList(ListVO listVO) {
		workListDAO.updateList(listVO);
		
	}

	@Override
	public void deleteList(int list_id) {
		workListDAO.deleteList(list_id);
		
	}
	
	@Override
	public List<CardVO> selectCard(String cardWsid) {
		
		return workListDAO.selectCard(cardWsid);
	}
	
	@Override
	public int selectCardId() {
		return workListDAO.selectCardId();
	}

	@Override
	@Transactional
	public void insertCard(CardLogVO cardLogVO) {
		//cardVO.setCard_id(workListDAO.selectCardId());
		workListDAO.insertCard(cardLogVO);
		
		
	}

	@Override
	public void updateCard(CardVO cardVO) {
		workListDAO.updateCard(cardVO);
		
	}

	@Override
	public void deleteCard(int card_id) {
		workListDAO.deleteCard(card_id);
		
	}

	@Override
	public int selectLastCardId() {
		return workListDAO.selectLastCardId();
		
	}

	@Override
	public void deleteCardAct(int card_id) {
		 workListDAO.deleteCardAct(card_id);
		
	}

	@Override
	public void deleteCardMember(int card_id) {
		 workListDAO.deleteCardMember(card_id);
		
	}

	@Override
	public void deleteCardTodo(int card_id) {
		 workListDAO.deleteCardTodo(card_id);
		
	}

	@Override
	public void deleteCardFile(int card_id) {
		 workListDAO.deleteCardFile(card_id);
		
	}
	

//	@Override
//	public List<CardVO> selectCartList(int list_id) {
//		
//		return workListDAO.selectCardList(list_id);
//	}

	

}
