package com.gotcha.www.workList.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gotcha.www.workList.dao.WorkListDAO;
import com.gotcha.www.workList.service.WorkListService;
import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;


@RestController
public class WorkListController {

	private final WorkListService workListService;
	private final Log log = LogFactory.getLog(this.getClass());

	@Autowired
	public WorkListController(WorkListService workListService){
		this.workListService = workListService;
	}
	
	//워크리스트에 리스트 뿌려줌
	@RequestMapping("/main/wsList/list")
	public @ResponseBody List<ListVO> selectList( @RequestBody HashMap<String,String> map)throws Exception{
		String listWsid = map.get("ws_id");
		log.info("\n"+"list work space id: "+listWsid);
		List<ListVO> listList = workListService.selectList(listWsid);
		listList.forEach(log::info);
		return listList;
	}
	
//	//워크리스트의 리스트에 카드 뿌려줌
//	@RequestMapping("/main/wsList/list/card")
//	public @ResponseBody List<Map<String, CardVO>> selectList(@RequestBody CardVO cardVO)throws Exception{
//		System.out.println("list_id: "+cardVO.getList_id());
//		List<Map<String, CardVO>> cardList = workListService.selectCard(cardVO);
//		System.out.println("cardList: "+cardList);
//		return cardList;
//	}
	//워크리스트의 리스트에 카드 뿌려줌
		@RequestMapping("/main/wsList/list/card")
		public @ResponseBody List<CardVO> selectCard(@RequestBody HashMap<String,String> map)throws Exception{
			String cardWsid = map.get("ws_id");
			System.out.println("cardWsid: "+cardWsid);
			List<CardVO> cardList = workListService.selectCard(cardWsid);
			System.out.println("cardList: "+cardList);
			return cardList;
		}
}
