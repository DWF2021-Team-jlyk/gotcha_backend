package com.gotcha.www.workList.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gotcha.www.workList.vo.CardLogVO;
import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;

@Mapper
public interface WorkListDAO {
	// List Mapper
	public List<ListVO> selectList(int listWsid);
	
	public int selectListId();
	
	void insertList(ListVO listVO);
	
	void updateList(ListVO listVO);
	
	void deleteList(int list_id);
	
	// Card Mapper

	//public List<Map<String, CardVO>> selectCard(CardVO cvo);
	
	public List<CardVO> selectCard(String cardWsid);
	
	public int selectCardId();
	
	void insertCard(CardLogVO cardLogVO);
	
	void updateCard(CardVO cardVO);
	
	void deleteCard(int card_id);

	CardVO selectOneCard(int card_id);
	//public List<CardVO> selectCardList(int list_id);
	
	int selectLastCardId();
	
}
