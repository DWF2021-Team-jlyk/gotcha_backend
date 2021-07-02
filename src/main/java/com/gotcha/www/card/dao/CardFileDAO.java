package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardFileDTO;

import java.util.List;

public interface CardFileDAO {
    int selectFileId();
    int fileCount(int card_id);
    List<CardFileDTO> selectCardFile(int card_id);
    void deleteCardFile(String file_id);
    void updateCardFile(CardFileDTO cardFile);
    void insertCardFile(CardFileDTO cardFile);
}
