package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardActDTO;
import com.gotcha.www.workList.vo.CardVO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CardActionDAO {
    int getMaxPosition(CardVO cardVO);
    void updateCardMove(CardVO cardVO);
    void updateDestPosition(CardVO cardVO);
    void updateNowPosition(CardVO cardVO);
}
