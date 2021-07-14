package com.gotcha.www.card.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.card.service.CardActService;
import com.gotcha.www.card.vo.CardActDTO;

@RequestMapping("/cardDetail/act")
@RestController
public class CardActController {
	
	private static final Logger log = LoggerFactory.getLogger(CardActController.class);


	private final CardActService cardActService;
	
	public CardActController(CardActService cardActService) {
		this.cardActService = cardActService;
	}
	
	@PostMapping("selectList")
	public @ResponseBody List<CardActDTO> getCardAct(@RequestBody  HashMap<String, String> map){

		String card_id = map.get("card_id");
        return cardActService.getCardAct(Integer.parseInt(card_id));
    }
	
	
	@PostMapping("insertCardAct")
	public @ResponseBody CardActDTO insertCardAct(@RequestBody CardActDTO cardActDTO){
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int act_id = cardActService.selectActId();

		cardActDTO.setAct_id(act_id);
		cardActDTO.setCreated_date(format.format(today));

		cardActService.insertCardAct(cardActDTO);
		log.info("insertCardAct cardActDTO after service"+cardActDTO);
		return cardActDTO;
    }
	
	@PostMapping("deleteCardAct")
	public @ResponseBody CardActDTO deleteCardAct(@RequestBody HashMap<String, String> map){
		String act_id = map.get("act_id");
		 CardActDTO cardActDTO = new CardActDTO();
		 cardActDTO.setAct_id(Integer.parseInt(act_id));
		
		cardActService.deleteCardAct(Integer.parseInt(act_id));
		return cardActDTO;
    }
	
	@PostMapping("updateCardAct")
	public @ResponseBody CardActDTO updateCardAct(@RequestBody CardActDTO cardActDTO) {
		cardActService.updateCardAct(cardActDTO);
		return cardActDTO;
	}
	

	

}
