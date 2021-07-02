package com.gotcha.www.card.vo;

import com.gotcha.www.workList.vo.CardVO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailVO {
    private CardVO cardVO;
    private List<CardActDTO> cardActs;
    private List<CardFileDTO> cardFiles;
    private List<CardMemberDTO> cardMembers;
    private List<CardTodoDTO> cardTodos;
}
