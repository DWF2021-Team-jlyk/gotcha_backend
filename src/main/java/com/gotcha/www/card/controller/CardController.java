package com.gotcha.www.card.controller;

import com.gotcha.www.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

public class CardController {

    private final CardService cardService;

    public CardController(@Autowired CardService cardService) {
        this.cardService = cardService;
    }


}
