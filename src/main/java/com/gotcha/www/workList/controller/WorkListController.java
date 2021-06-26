package com.gotcha.www.workList.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.workList.dao.WorkListDAO;
import com.gotcha.www.workList.service.WorkListService;
import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;


@RestController
public class WorkListController {
	
	@Autowired
	WorkListService wokrListService;
	
	@Autowired
	WorkListDAO workListDAO;
	
	@RequestMapping("/main/wsList/list") //@PathVariable("ws_id") String getWsId,
	public @ResponseBody List<ListVO> selectList( @RequestBody HashMap<String,String> map)throws Exception{
		String wsId = map.get("ws_id");
		System.out.println(wsId);
		List<ListVO> listList = wokrListService.selectList(wsId);
//		for(ListVO list : listList) {
//		List<CardVO> cardList = wokrListService.selectCartList(list.getList_id());
//		list.setCard(cardList);
//		}
		System.out.println("listList: "+listList);
		//System.out.println(listVO.getWs_id());
		
		//mapper.selectList(listVO).forEach(log::info);
		return listList;
	}
	
	@RequestMapping("/main/wsList/list/card")
	public @ResponseBody List<Map<String,CardVO>> selectList(@RequestBody CardVO cardVO)throws Exception{
		System.out.println("list_id: "+cardVO.getList_id());
		List<Map<String,CardVO>> cardList = wokrListService.selectCard(cardVO);
		System.out.println("cardList: "+cardList);
		return cardList;
	}
}
