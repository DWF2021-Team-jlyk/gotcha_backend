package com.gotcha.www.card.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.card.service.CardMemberService;
import com.gotcha.www.card.vo.CardMemberDTO;

@RequestMapping("/cardDetail/member")
@RestController
public class CardMemberController {
	private final CardMemberService cardMemberService;

	public CardMemberController(CardMemberService cardMemberService) {
		this.cardMemberService = cardMemberService;
	}
	
	@PostMapping("")
    public @ResponseBody List<CardMemberDTO> getCardMem(@RequestBody HashMap<String, String> map){
    	String card_id = map.get("card_id");
    	System.out.println("card member"+card_id);	
    	List<CardMemberDTO> list = cardMemberService.getCardMem(Integer.parseInt(card_id));
        return list;
    }
}
