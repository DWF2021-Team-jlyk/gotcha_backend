package com.gotcha.www.workList.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gotcha.www.card.service.CardActService;
import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.user.vo.PrincipalDetails;
import com.gotcha.www.workList.dao.WorkListDAO;
import com.gotcha.www.workList.service.WorkListService;
import com.gotcha.www.workList.vo.CardLogVO;
import com.gotcha.www.workList.vo.CardVO;
import com.gotcha.www.workList.vo.ListVO;

@RequestMapping("/main/wsList/list")
@RestController
public class WorkListController {

    @Autowired
    WorkListService workListService;

    @Autowired
    WorkListDAO workListDAO;

	@Autowired
	CardActService cardActService;

    private Log log = LogFactory.getLog(this.getClass());

    // List CRUD
    @RequestMapping("")
    public @ResponseBody
    List<ListVO> selectList(@RequestBody HashMap<String, String> map) throws Exception {
        String listWsid = map.get("ws_id");

        log.info("map" + map);
        log.info("listWsid: " + listWsid);
        log.info("selectList : " + listWsid.getClass());

        List<ListVO> listList = workListService.selectList(Integer.parseInt(listWsid));

//        log.info("lists: " + listList);
        // 리스트와 카드를 한 번에 가져오고 싶으면 아래 주석 해제
//		for(ListVO list : listList) {
//		List<CardVO> cardList = wokrListService.selectCartList(list.getList_id());
//		list.setCard(cardList);
//		}

		return listList;
	}

	@RequestMapping("/insert")
	public @ResponseBody ListVO insertList(@RequestBody ListVO listVO) {
		log.info(listVO);
    	listVO.setList_id(workListService.selectListId());
		log.info("listVO insert info after:"+listVO);
		workListService.insertList(listVO);
		return listVO;
	}

	@RequestMapping("/update")
	public @ResponseBody ListVO updateList(@RequestBody ListVO listVO) {
		log.info(listVO);
		workListService.updateList(listVO);
		return listVO;
	}

	@RequestMapping("/delete")
	public @ResponseBody ListVO deleteList(@RequestBody ListVO listVO) {
    	log.info(listVO);
		workListService.deleteList(listVO.getList_id());
		return listVO;
	}

	// Card CRUD
	@RequestMapping("/card")
	public @ResponseBody List<CardVO> selectCard(@RequestBody HashMap<String, String> map) throws Exception {
		String cardWsid = map.get("ws_id");

		log.info("\ncardWsid: " + cardWsid);

		List<CardVO> cardList = workListService.selectCard(cardWsid);

		return cardList;
	}

	@RequestMapping("/card/insert")
	public @ResponseBody CardLogVO insertCard(@RequestBody CardLogVO cardLogVO) {
    	log.info("cardLogVO11" + cardLogVO);
    	int card_id = workListService.selectCardId();
    	String desc = cardLogVO.getUser_id() + "(이)가 카드 " + cardLogVO.getCard_name() + "(을)를 추가했습니다.";
    	cardLogVO.setCard_id(card_id);
    	log.info("cardLogVO222" + cardLogVO);
		workListService.insertCard(cardLogVO);

		
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int act_id = cardActService.selectActId();

		CardActDTO cardActDTO = new CardActDTO();
		cardActDTO.setAct_id(act_id);
		cardActDTO.setCreated_date(format.format(today).toString());
		cardActDTO.setCard_id(card_id);
		cardActDTO.setIslog("1");
		cardActDTO.setUser_id(cardLogVO.getUser_id());
		cardActDTO.setAct_desc(desc);
		
    	log.info("cardActDTO" + cardActDTO);	
    	
		cardActService.insertCardAct(cardActDTO);
		
	
		return cardLogVO;
	}

	@RequestMapping("card/update")
	public @ResponseBody CardVO updateCard(@RequestBody CardVO cardVO) {
    	log.info("\n updateCard : " + cardVO);
		workListService.updateCard(cardVO);
		return cardVO;
	}
	
	@RequestMapping("/card/delete")
	public CardVO deleteCard(@RequestBody CardVO cardVO) {
		workListService.deleteCard(cardVO.getCard_id());
		workListService.deleteCardAct(cardVO.getCard_id());
		workListService.deleteCardMember(cardVO.getCard_id());
		workListService.deleteCardTodo(cardVO.getCard_id());
		workListService.deleteCardFile(cardVO.getCard_id());
		
		return cardVO;
		
	}
	
	@RequestMapping("/card/selectLastCardId")
	public int selectLastCardId() {
		int cardid = workListService.selectLastCardId();
		System.out.println("selectLastCardId"+ cardid);
		return cardid;
	}
	
}
