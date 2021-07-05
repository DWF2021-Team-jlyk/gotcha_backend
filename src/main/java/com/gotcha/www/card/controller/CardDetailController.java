package com.gotcha.www.card.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.card.service.CardDetailService;
import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.card.vo.CardFileDTO;
import com.gotcha.www.card.vo.CardMemberDTO;
import com.gotcha.www.card.vo.CardTodoDTO;

@RequestMapping("/cardDetail")
@RestController
public class CardDetailController {
    private final CardDetailService cardDetailService;

    public CardDetailController(CardDetailService cardDetailService) {
        this.cardDetailService = cardDetailService;
    }
    //act
    @PostMapping("/act")
    public @ResponseBody List<CardActDTO> getCardAct(@RequestBody int card_id){
   
    	System.out.println(cardDetailService.getCardAct(card_id));
        return cardDetailService.getCardAct((card_id));
    }
    
    //todo
    @PostMapping("/todo")
    public @ResponseBody List<CardTodoDTO> getCardTodo(@RequestBody HashMap<String, String> map){
    	String card_id = map.get("card_id");
    	List<CardTodoDTO> todoList = cardDetailService.getCardTodo(Integer.parseInt(card_id));
        return todoList;
    }
    
    
    //file
    @PostMapping("/file")
    public @ResponseBody List<CardFileDTO> getCardFile(@RequestBody int card_id){
        return cardDetailService.getCardFile(card_id);
    }
    
    //member
    @PostMapping("/member")
    public @ResponseBody List<CardMemberDTO> getCardMem(@RequestBody HashMap<String, String> map){
    	String card_id = map.get("card_id");
    	List<CardMemberDTO> list = cardDetailService.getCardMem(Integer.parseInt(card_id));
        return list;
    }
}
