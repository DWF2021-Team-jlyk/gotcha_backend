package com.gotcha.www.card.dao;

import com.gotcha.www.card.vo.CardFileDTO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardFileDAO {
	int selectFileId();

	int fileCount(int card_id);

	List<CardFileDTO> selectCardFile(int card_id);

	CardFileDTO selectOneCardFile(int file_id);

	void deleteCardFile(int file_id);

	// CardFileDTO deleteCardFile(int file_id);
	// CardFileDTO deleteCardFile(CardFileDTO cardFileDTO);
	void updateCardFile(CardFileDTO cardFileDTO);

	void insertCardFile(CardFileDTO cardFileDTO);
}
