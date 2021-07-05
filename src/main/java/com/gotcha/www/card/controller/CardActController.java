package com.gotcha.www.card.controller;

import java.util.List;

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

	private final CardActService cardActService;
	
	public CardActController(CardActService cardActService) {
		this.cardActService = cardActService;
	}
	
	@PostMapping("")
	public @ResponseBody List<CardActDTO> getCardAct(@RequestBody int card_id){
		   
    	System.out.println(cardActService.getCardAct(card_id));
        return cardActService.getCardAct((card_id));
    }

}
