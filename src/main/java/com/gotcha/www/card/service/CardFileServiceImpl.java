package com.gotcha.www.card.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gotcha.www.card.dao.CardFileDAO;
import com.gotcha.www.card.vo.CardFileDTO;
import com.gotcha.www.workList.dao.WorkListDAO;

@Service
@Transactional
public class CardFileServiceImpl implements CardFileService {

	private final Log log = LogFactory.getLog(this.getClass());

	private final WorkListDAO workListDAO;
	private final CardFileDAO cardFileDAO;
	
	@Value("${service.file.uploadurl}")
	private String fileUploadPath;

	@Autowired
	public CardFileServiceImpl(WorkListDAO workListDAO, CardFileDAO cardFileDAO) {
		this.workListDAO = workListDAO;
		this.cardFileDAO = cardFileDAO;
	}
	
	@Override
	public void init() {
		try {
			Files.createDirectories(getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			InputStream inputStream = file.getInputStream();
			Files.copy(inputStream, getPath().resolve(filename),
					StandardCopyOption.REPLACE_EXISTING);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<CardFileDTO> getCardFile(int card_id) {
		return cardFileDAO.selectCardFile(card_id);
	}

	@Override
	public CardFileDTO getOneCardFile(int file_id) {
		return cardFileDAO.selectOneCardFile(file_id);
	}


	@Override
	public Path getPath() {
		return Paths.get(fileUploadPath);
	}

	@Override
	public int selectFileId() {
		return cardFileDAO.selectFileId();
	}

	@Override
	public void insertCardFile(CardFileDTO cardFileDTO) {
		cardFileDAO.insertCardFile(cardFileDTO);
		
	}
	
//	@Override
//	public List<CardFileDTO> deleteCardFile(int file_id) {
//		return cardFileDAO.deleteCardFile(file_id);
//	}

	@Override
	public void deleteCardFile(int file_id) {
		log.info(file_id);
		cardFileDAO.deleteCardFile(file_id);
	}

	@Override
	public void updateCardFile(CardFileDTO cardFileDTO) {
		cardFileDAO.updateCardFile(cardFileDTO);
		
	}

	@Override
	public CardFileDTO getJson(String cardFileDTO, List<MultipartFile> file) {
		CardFileDTO cardFileDTOJson = new CardFileDTO();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			cardFileDTOJson = objectMapper.readValue(cardFileDTO, CardFileDTO.class);
		}catch(IOException err) {
			System.out.printf("Error", err.toString());
		}
		int fileId = cardFileDAO.selectFileId();
		String fileName = ((MultipartFile) file).getOriginalFilename();
		cardFileDTOJson.setFile_id(fileId);
		cardFileDTOJson.setFile_name(fileName);
		return cardFileDTOJson;
	}

//	@Override
//	public CardFileDTO deleteCardFile(CardFileDTO cardFileDTO) {
//		return cardFileDAO.deleteCardFile(cardFileDTO.getFile_id());
//	}
//	
//	@Override
//	public CardFileDTO deleteCardFile(int file_id) {
//		return cardFileDAO.deleteCardFile(file_id);
//	}


	

}
