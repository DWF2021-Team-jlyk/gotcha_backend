package com.gotcha.www.workList.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gotcha.www.card.dao.CardActDAO;
import com.gotcha.www.card.dao.CardFileDAO;
import com.gotcha.www.card.dao.CardMemberDAO;
import com.gotcha.www.card.dao.CardTodoDAO;
import com.gotcha.www.card.vo.CardTodoDTO;
import com.gotcha.www.extra.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.workList.dao.WorkListDAO;
import com.gotcha.www.workList.vo.CardLogVO;
import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;


@Service
public class WorkListServiceImpl implements WorkListService {

	private final WorkListDAO workListDAO;

	private final CardActDAO cardActDAO;
	private final CardMemberDAO cardMemberDAO;
	private final CardFileDAO cardFileDAO;
	private final CardTodoDAO cardTodoDAO;

	private final FileService fileService;

	@Autowired
	public WorkListServiceImpl(
			WorkListDAO workListDAO, CardActDAO cardActDAO,
			CardMemberDAO cardMemberDAO, CardFileDAO cardFileDAO,
			CardTodoDAO cardTodoDAO, FileService fileService){
		this.workListDAO = workListDAO;

		this.cardActDAO = cardActDAO;
		this.cardMemberDAO = cardMemberDAO;
		this.cardFileDAO = cardFileDAO;
		this.cardTodoDAO = cardTodoDAO;

		this.fileService = fileService;
	}

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
	@Transactional
	public void deleteList(int list_id) {
		List<CardVO> cardList = workListDAO.selectCardList(list_id);

		workListDAO.deleteList(list_id);
		cardList.forEach(card->{
			deleteCard(card.getCard_id());
			try{
				fileService.deleteAllFile(card.getCard_id(), "cards");

			} catch (IOException e){
				e.printStackTrace();
			}
		});
	}
	
	@Override
	public List<CardVO> selectCard(String cardWsid) {
		return workListDAO.selectCard(cardWsid);
	}
	
	@Override
	@Transactional
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
	@Transactional
	public void deleteCard(int card_id) {
		workListDAO.deleteCard(card_id);
		cardTodoDAO.deleteCard(card_id);
		cardFileDAO.deleteCard(card_id);
		cardActDAO.deleteCard(card_id);
		cardMemberDAO.deleteCard(card_id);
	}

	@Override
	public int selectLastCardId() {
		return workListDAO.selectLastCardId();
		
	}
	

//	@Override
//	public List<CardVO> selectCartList(int list_id) {
//		
//		return workListDAO.selectCardList(list_id);
//	}

	

}
