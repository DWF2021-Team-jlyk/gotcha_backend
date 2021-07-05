package com.gotcha.www.card.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.card.service.CardFileService;
import com.gotcha.www.card.vo.CardFileDTO;

@RequestMapping("/cardDetail/file")
@RestController
public class CardFileController {
	private final CardFileService cardFileService;

	public CardFileController(CardFileService cardFileService) {
		this.cardFileService = cardFileService;
	}
	
	@PostMapping("")
    public @ResponseBody List<CardFileDTO> getCardFile(@RequestBody int card_id){
        return cardFileService.getCardFile(card_id);
    }
}
