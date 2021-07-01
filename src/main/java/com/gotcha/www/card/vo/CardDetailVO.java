package com.gotcha.www.card.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailVO {
    private CardDTO cardDTO;
    private List<CardActDTO> cardActs;
    private List<CardFileDTO> cardFiles;
    private List<CardMemberDTO> cardMembers;
    private List<CardTodoDTO> cardTodos;
}
