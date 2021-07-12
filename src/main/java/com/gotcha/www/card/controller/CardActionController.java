package com.gotcha.www.card.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.card.service.CardActionService;
import com.gotcha.www.workList.service.WorkListService;
import com.gotcha.www.workList.vo.CardVO;

@RequestMapping("/cardDetail/Action")
@RestController
public class CardActionController {
	
	private final CardActionService cardActionService;
	
	@Autowired
	WorkListService workListService;
	
	public CardActionController(CardActionService cardActionService) {
		this.cardActionService = cardActionService;
	}
	
	@PostMapping("selectMaxPosition")
	public @ResponseBody int getMaxPosition(@RequestBody CardVO cardVO) {
		int maxPosition = cardActionService.getMaxPosition(cardVO);
		return maxPosition;
	}
	
	@PostMapping("updateCardMove")
	public @ResponseBody CardVO updateCardMove(@RequestBody CardVO cardVO) {
		System.out.println("cardVO" + cardVO);
		cardActionService.updateCardMove(cardVO);
		cardActionService.updateDestPosition(cardVO);
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
