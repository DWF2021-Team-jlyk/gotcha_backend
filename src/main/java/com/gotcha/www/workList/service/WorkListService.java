package com.gotcha.www.workList.service;

import java.util.List;
import java.util.Map;

import com.gotcha.www.workList.vo.CardLogVO;
import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;

public interface WorkListService {
	// List Service
	
	int selectListId();

	void insertList(ListVO listVO);

	void updateList(ListVO listVO);

	void deleteList(int list_id);

	// List<Map<String, CardVO>> selectCard(CardVO cvo);
	List<CardVO> selectCard(String cardWsid);
	
	int selectCardId();

	void insertCard(CardLogVO cardLogVO);

	void updateCard(CardVO cardVO);

	void deleteCard(int card_id);

	List<ListVO> selectList(int listWsid);

	int selectLastCardId();
	//List<CardVO> selectCartList(int list_id);
}
