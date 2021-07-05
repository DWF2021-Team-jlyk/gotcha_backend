package com.gotcha.www.card.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
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
	private final Log log = LogFactory.getLog(this.getClass());

	public CardMemberController(CardMemberService cardMemberService) {
		this.cardMemberService = cardMemberService;
	}
	
	@PostMapping("/insertCardMember")
	 public @ResponseBody CardMemberDTO insertCardMember(@RequestBody CardMemberDTO cardMemberDTO){
		log.info("\n + cardMemberDTO" + cardMemberDTO);
		cardMemberService.insertCardMember(cardMemberDTO);
		return cardMemberDTO;
    }
	
	@PostMapping("/selectList")
    public @ResponseBody List<CardMemberDTO> getCardMem(@RequestBody HashMap<String, String> map){
		System.out.println(map);
    	String card_id = map.get("card_id");
    	System.out.println("--------------------------------------------" + card_id);
    	List<CardMemberDTO> list = cardMemberService.getCardMem(Integer.parseInt(card_id));
        return list;
    }
}
