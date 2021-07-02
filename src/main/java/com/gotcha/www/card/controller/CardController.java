package com.gotcha.www.card.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.card.service.CardDetailService;
import com.gotcha.www.card.service.CardService;
import com.gotcha.www.card.vo.CardDTO;
import com.gotcha.www.card.vo.CardDetailVO;
import com.gotcha.www.card.vo.CardTodoDTO;

@RestController
@RequestMapping("/card")
public class CardController {
	@Autowired
	CardService cardService;
	
	@Autowired
	CardDetailService cardDetailService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/cardDetail")
    public @ResponseBody CardDetailVO getCardDetail(@RequestBody CardDTO card) {
		
		CardDetailVO cardDetail =  cardDetailService.getCardInfo(card.getCard_id());
		logger.info(cardDetail.toString());

		return cardDetail;
    }
	
	@PostMapping("/todoIsDoneChange")
    public @ResponseBody void changeTodoIsDone(@RequestBody CardTodoDTO cardTodoDTO) {
		cardDetailService.updateTodoIsDone(cardTodoDTO);
	
    }


}
