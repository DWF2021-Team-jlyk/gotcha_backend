package com.gotcha.www.card.service;

<<<<<<< HEAD
import com.gotcha.www.card.vo.*;

import java.util.List;
import java.util.Optional;

public interface CardDetailService {

    List<CardActDTO> getCardAct(int card_id);
    List<CardMemberDTO> getCardMem(int card_id);
    List<CardFileDTO> getCardFile(int card_id);
    List<CardTodoDTO> getCardTodo(int card_id);
=======
import com.gotcha.www.card.vo.CardDetailVO;
import com.gotcha.www.card.vo.CardMemberDTO;
import com.gotcha.www.card.vo.CardTodoDTO;


public interface CardDetailService {
    CardDetailVO getCardInfo(int card_id);
    void updateTodoIsDone(CardTodoDTO cardTodoDTO);
    void insertCardMember(CardMemberDTO cardMemberDTO);
>>>>>>> 62c348b280fa1c257dfed7aedd35adf9f1a30f97
}
