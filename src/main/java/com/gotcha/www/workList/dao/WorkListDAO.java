package com.gotcha.www.workList.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;

@Mapper
public interface WorkListDAO {
	public List<ListVO> selectList(String wsId);

	public List<Map<String, CardVO>> selectCard(CardVO cvo);

	public List<CardVO> selectCardList(int list_id);
}
