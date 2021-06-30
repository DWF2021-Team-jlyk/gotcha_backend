package com.gotcha.www.workList.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.user.vo.PrincipalDetails;
import com.gotcha.www.workList.dao.WorkListDAO;
import com.gotcha.www.workList.service.WorkListService;
import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;

@RestController
@RequestMapping("/main/wsList/list")
public class WorkListController {

	@Autowired
	WorkListService workListService;

	@Autowired
	WorkListDAO workListDAO;

	private Log log = LogFactory.getLog(this.getClass());

	// List CRUD
	@RequestMapping("")
	public @ResponseBody List<ListVO> selectList(@RequestBody HashMap<String, String> map) throws Exception {
		String listWsid = map.get("ws_id");

		log.info("listWsid: " + listWsid);

		List<ListVO> listList = workListService.selectList(listWsid);

		log.info("lists: " + listList);
		// 리스트와 카드를 한 번에 가져오고 싶으면 아래 주석 해제
//		for(ListVO list : listList) {
//		List<CardVO> cardList = wokrListService.selectCartList(list.getList_id());
//		list.setCard(cardList);
//		}

		return listList;
	}

	@RequestMapping("/insert")
	public @ResponseBody ListVO insertList(@RequestBody ListVO listVO) {
		listVO.setList_id(workListService.selectListId());
		workListService.insertList(listVO);
		return listVO;
	}

	@RequestMapping("/update")
	public void updateList(@RequestBody ListVO listVO) {
		// System.out.println("here1");
		System.out.println(listVO);
		workListService.updateList(listVO);
		// System.out.println("here");
	}

	@RequestMapping("/delete")
	public void deleteList(@RequestBody ListVO listVO) {
		workListService.deleteList(listVO.getList_id());
	}

	// Card CRUD
	@RequestMapping("/card")
	public @ResponseBody List<CardVO> selectCard(@RequestBody HashMap<String, String> map) throws Exception {
		String cardWsid = map.get("ws_id");

		log.info("\ncardWsid: " + cardWsid);

		List<CardVO> cardList = workListService.selectCard(cardWsid);

		log.info("cards: " + cardList);

		return cardList;
	}

	@RequestMapping("/card/insert")
	public void insertCard(@RequestBody CardVO cardVO) {
		System.out.println(cardVO);
		workListService.insertCard(cardVO);
		System.out.println(cardVO);
	}
	
<<<<<<< HEAD
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
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MEMBER')")
	public @ResponseBody List<CardVO> selectCard(@RequestBody HashMap<String,String> map)throws Exception{
		String cardWsid = map.get("ws_id");
		System.out.println("cardWsid: "+cardWsid);
		List<CardVO> cardList = wokrListService.selectCard(cardWsid);
		System.out.println("cardList: "+cardList);
		return cardList;
=======
	@RequestMapping("card/update")
	public void updateCard(@RequestBody CardVO cardVO) {
		workListService.updateCard(cardVO);
	}
	
	@RequestMapping("card/delete")
	public void deleteCard(@RequestBody CardVO cardVO) {
		workListService.deleteCard(cardVO.getCard_id());
>>>>>>> origin/taeheon
	}
}
