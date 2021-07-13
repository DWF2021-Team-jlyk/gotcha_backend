package com.gotcha.www.card.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.card.service.CardTodoService;
import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.card.vo.CardTodoDTO;
import com.gotcha.www.workList.vo.ListVO;

@RequestMapping("/cardDetail/todo")
@RestController
public class CardTodoController {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private final CardTodoService cardTodoService;

	public CardTodoController(CardTodoService cardTodoService) {
		this.cardTodoService = cardTodoService;
	}
	
	@PostMapping("")
    public @ResponseBody List<CardTodoDTO> getCardTodo(@RequestBody HashMap<String, String> map){
    	String card_id = map.get("card_id");
    	log.info("CardoTodoController card_id:"+card_id);
    	List<CardTodoDTO> todoList = cardTodoService.getCardTodo(Integer.parseInt(card_id));
        return todoList;

    }
	
	@RequestMapping("/insert")
	public @ResponseBody CardTodoDTO insertCardTodo(@RequestBody CardTodoDTO cardTodoDTO) {
		log.info("controller, service before:"+cardTodoDTO);
		cardTodoDTO.setTodo_id(cardTodoService.selectTodoId());
		cardTodoService.insertCardTodo(cardTodoDTO);
		log.info("constroller, service after:"+cardTodoDTO);
		return cardTodoDTO;
	}

	@RequestMapping("/update")
	public @ResponseBody CardTodoDTO updateCardTodo(@RequestBody CardTodoDTO cardTodoDTO) {
		System.out.println(cardTodoDTO);
		cardTodoService.updateCardTodo(cardTodoDTO);
		return cardTodoDTO;
	}

	@RequestMapping("/delete")
	public @ResponseBody CardTodoDTO deleteCardTodo(@RequestBody CardTodoDTO cardTodoDTO) {
    	log.info("delete controller, service befor"+cardTodoDTO);
    	cardTodoService.deleteCardTodo(cardTodoDTO.getTodo_id());
    	log.info("delete controller, service after getTodo_id()"+cardTodoDTO.getTodo_id());
    	log.info("delete controller, service after"+cardTodoDTO);
		return cardTodoDTO;
	}

}
