package com.gotcha.www.card.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.card.service.CardActService;
import com.gotcha.www.card.service.CardActionService;
import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.workList.service.WorkListService;
import com.gotcha.www.workList.vo.CardVO;

@RequestMapping("/cardDetail/Action")
@RestController
public class CardActionController {
	
	private final CardActionService cardActionService;

	private final WorkListService workListService;

	private final CardActService cardActService;

	@Autowired
	public CardActionController(
			CardActionService cardActionService,
			WorkListService workListService,
			CardActService cardActService
	) {
		this.cardActionService = cardActionService;
		this.workListService = workListService;
		this.cardActService = cardActService;
	} 
	
	@PostMapping("selectMaxPosition")
	public @ResponseBody int getMaxPosition(@RequestBody CardVO cardVO) {
		int maxPosition = cardActionService.getMaxPosition(cardVO);
		return maxPosition;
	}
	
	@PostMapping("updateCardMove")
	@Transactional
	public @ResponseBody CardVO updateCardMove(@RequestBody CardVO cardVO) {
		System.out.println("cardVO" + cardVO);

		cardActionService.updateDestPosition(cardVO);
		cardActionService.updateCardMove(cardVO);


		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int act_id = cardActService.selectActId();
		
		CardActDTO cardActDTO = new CardActDTO();
		cardActDTO.setCard_id(cardVO.getCard_id());
		cardActDTO.setAct_id(act_id);
		cardActDTO.setUser_id("user01@naver.com");
		cardActDTO.setCreated_date(format.format(today));
		cardActDTO.setIslog("1");
		cardActDTO.setAct_desc("user_name(이)가 card를 " + cardVO.getList_id()  + "(으)로 이동하였습니다.");	
		cardActDTO.setIsedit("0");
		cardActService.insertCardAct(cardActDTO);
		
		return cardVO;
	}
	
	
//	@PostMapping("updateDestPosition")
//	public @ResponseBody List<CardVO> updateDestPosition(@RequestBody CardVO cardVO){
//		cardActionService.updateDestPosition(cardVO);
//		String ws_id = Integer.toString(cardVO.getWs_id());
//		List<CardVO> cardList = workListService.selectCard(ws_id);
//		return cardList;
//	}
	
	
	@PostMapping("updateNowPosition")
	public @ResponseBody CardVO updateNowPosition(@RequestBody CardVO cardVO) {
		cardActionService.updateNowPosition(cardVO);
		return cardVO;
	}
}
