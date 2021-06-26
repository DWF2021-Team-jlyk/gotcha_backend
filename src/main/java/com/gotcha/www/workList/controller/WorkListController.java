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
	
	//워크리스트에 리스트 뿌려줌
	@RequestMapping("/main/wsList/list") 
	public @ResponseBody List<ListVO> selectList( @RequestBody HashMap<String,String> map)throws Exception{
		String listWsid = map.get("ws_id");
		System.out.println("listWsid: "+listWsid);
		List<ListVO> listList = wokrListService.selectList(listWsid);
		//리스트와 카드를 한 번에 가져오고 싶으면 아래 주석 해제
//		for(ListVO list : listList) {
//		List<CardVO> cardList = wokrListService.selectCartList(list.getList_id());
//		list.setCard(cardList);
//		}
		System.out.println("listList: "+listList);
		//System.out.println(listVO.getWs_id());
		
		return listList;
	}
	
//	//워크리스트의 리스트에 카드 뿌려줌
//	@RequestMapping("/main/wsList/list/card")
//	public @ResponseBody List<Map<String, CardVO>> selectList(@RequestBody CardVO cardVO)throws Exception{
//		System.out.println("list_id: "+cardVO.getList_id());
//		List<Map<String, CardVO>> cardList = wokrListService.selectCard(cardVO);
//		System.out.println("cardList: "+cardList);
//		return cardList;
//	}
	//워크리스트의 리스트에 카드 뿌려줌
		@RequestMapping("/main/wsList/list/card")
		public @ResponseBody List<CardVO> selectCard(@RequestBody HashMap<String,String> map)throws Exception{
			String cardWsid = map.get("ws_id");
			System.out.println("cardWsid: "+cardWsid);
			List<CardVO> cardList = wokrListService.selectCard(cardWsid);
			System.out.println("cardList: "+cardList);
			return cardList;
		}
}
