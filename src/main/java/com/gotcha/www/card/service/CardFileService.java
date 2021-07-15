package com.gotcha.www.card.service;

import java.nio.file.Path;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gotcha.www.card.vo.CardFileDTO;


public interface CardFileService {
//	List<CardFileDTO> getCardFile(CardFileDTO cardFileDTO);
	List<CardFileDTO> getCardFile(int card_id);

	CardFileDTO getOneCardFile(int file_id);
	
	int selectFileId();
	
	void insertCardFile(CardFileDTO cardFileDTO);
	
	void updateCardFile(CardFileDTO cardFileDTO);
	
	void deleteCardFile(int file_id);
	
	//CardFileDTO deleteCardFile(CardFileDTO cardFileDTO);
	
	CardFileDTO getJson(String cardFileDTO, List<MultipartFile> file);
	
	void store(MultipartFile file);

	void init();
	
	Path getPath();
	
}
