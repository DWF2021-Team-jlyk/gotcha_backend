package com.gotcha.www.workList.service;

import java.util.List;
import java.util.Map;

import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;



public interface WorkListService {
	List<ListVO> selectList(String listWsid);
	//List<Map<String, CardVO>> selectCard(CardVO cvo);
	List<CardVO> selectCard(String cardWsid);
	List<CardVO> selectCartList(int list_id);
}
