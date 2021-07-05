package com.gotcha.www.card.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.card.service.CardTodoService;
import com.gotcha.www.card.vo.CardTodoDTO;

@RequestMapping("/cardDetail/todo")
@RestController
public class CardTodoController {
	private final CardTodoService cardDetailService;

	public CardTodoController(CardTodoService cardDetailService) {
		this.cardDetailService = cardDetailService;
	}
	
	@PostMapping("")
    public @ResponseBody List<CardTodoDTO> getCardTodo(@RequestBody HashMap<String, String> map){
    	String card_id = map.get("card_id");
    	List<CardTodoDTO> todoList = cardDetailService.getCardTodo(Integer.parseInt(card_id));
        return todoList;
    }
}
