package com.gotcha.www.card.controller;

import com.gotcha.www.card.service.CardDetailService;
import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.card.vo.CardFileDTO;
import com.gotcha.www.card.vo.CardMemberDTO;
import com.gotcha.www.card.vo.CardTodoDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cardDetail")
@RestController
public class CardDetailController {
    private final CardDetailService cardDetailService;

    public CardDetailController(CardDetailService cardDetailService) {
        this.cardDetailService = cardDetailService;
    }

    @PostMapping("/act")
    public @ResponseBody List<CardActDTO> getCardAct(@RequestBody int card_id){
        return cardDetailService.getCardAct(card_id);
    }

    @PostMapping("/todo")
    public @ResponseBody List<CardTodoDTO> getCardTodo(@RequestBody int card_id){
        return cardDetailService.getCardTodo(card_id);
    }
    @PostMapping("/file")
    public @ResponseBody List<CardFileDTO> getCardFile(@RequestBody int card_id){
        return cardDetailService.getCardFile(card_id);
    }
    @PostMapping("/member")
    public @ResponseBody List<CardMemberDTO> getCardMem(@RequestBody int card_id){
        return cardDetailService.getCardMem(card_id);
    }
}
