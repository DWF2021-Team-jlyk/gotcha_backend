package com.gotcha.www.workList.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotcha.www.workList.dao.WorkListDAO;
import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;


@Service
public class WorkListServiceImpl implements WorkListService {
	
	@Autowired
	WorkListDAO workListDAO;

	@Override
	public List<ListVO> selectList(String wsId) {
		
		return workListDAO.selectList(wsId);
	}

	@Override
	public List<Map<String, CardVO>> selectCard(CardVO cvo) {
		
		return workListDAO.selectCard(cvo);
	}

	@Override
	public List<CardVO> selectCartList(int list_id) {
		
		return workListDAO.selectCardList(list_id);
	}

	

}
