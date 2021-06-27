package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.card.vo.CardFileDTO;
import com.gotcha.www.card.vo.CardMemberDTO;
import com.gotcha.www.card.vo.CardTodoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CardDetailDAO {

    List<CardTodoDTO> selectTodoList(int card_id);
    List<CardMemberDTO> selectMember(int card_id);
    List<CardFileDTO> selectFile(int card_id);
    List<CardActDTO> selectCardAct(int card_id);

}
