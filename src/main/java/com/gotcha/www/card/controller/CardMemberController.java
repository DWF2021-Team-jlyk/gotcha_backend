package com.gotcha.www.card.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.gotcha.www.home.service.NotiService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.card.service.CardActService;
import com.gotcha.www.card.service.CardMemberService;
import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.card.vo.CardMemberDTO;

@RequestMapping("/cardDetail/member")
@RestController
public class CardMemberController {

	private final CardMemberService cardMemberService;
	private final NotiService notiService;
	private final CardActService cardActService;

	private final Log log = LogFactory.getLog(this.getClass());

	@Autowired
	public CardMemberController(
			CardMemberService cardMemberService,
			NotiService notiService,
			CardActService cardActService
	) {
		this.cardMemberService = cardMemberService;
		this.notiService = notiService;
		this.cardActService = cardActService;
	}
	
	@PostMapping("/insertCardMember")
	 public @ResponseBody CardMemberDTO insertCardMember(@RequestBody CardMemberDTO cardMemberDTO){
		log.info("\n + cardMemberDTO" + cardMemberDTO);
		cardMemberService.insertCardMember(cardMemberDTO);
		
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int act_id = cardActService.selectActId();

		notiService.makeCardNoti(cardMemberDTO.getCard_id(), cardMemberDTO.getUser_id());
		
		return cardMemberDTO;
    }
	
	@PostMapping("/selectList")
    public @ResponseBody List<CardMemberDTO> getCardMem(@RequestBody HashMap<String, Object> map){
//		log.info("getCardMember parameter" + map);
//		log.info("getCardMember " + map.get("card_id").getClass());
    	int card_id = (int)map.get("card_id");
//    	log.info("card_id" + card_id);
    	List<CardMemberDTO> list = cardMemberService.getCardMem(card_id);

        return list;
    }
	
	@PostMapping("/deleteCardMember")
	public @ResponseBody CardMemberDTO deleteCardMember(@RequestBody CardMemberDTO cardMemberDTO) {
		cardMemberService.deleteCardMember(cardMemberDTO);
		notiService.cancelCardNoti(cardMemberDTO.getCard_id(), cardMemberDTO.getUser_id());
		return cardMemberDTO;
	}
}
