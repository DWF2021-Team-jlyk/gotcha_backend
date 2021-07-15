package com.gotcha.www.card.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gotcha.www.card.service.CardFileService;
import com.gotcha.www.card.vo.CardFileDTO;


@RestController
@RequestMapping("/cardDetail/file")
public class CardFileController {
	private final CardFileService cardFileService;

//	private static final Logger log = LoggerFactory.getLogger(CardFileController.class);
	private final Log log = LogFactory.getLog(this.getClass());
	
	private FileOutputStream fos = null;

	public CardFileController(CardFileService cardFileService) {
		this.cardFileService = cardFileService;
	}

	@PostMapping("")
	public @ResponseBody List<CardFileDTO> getCardFile(@RequestBody HashMap<String, String> map) {
		String card_id = map.get("card_id");
		log.info("CardFileController card_id : " + card_id);
		List<CardFileDTO> fileList = cardFileService.getCardFile(Integer.parseInt(card_id));
		log.info("[SELECT CardFileController fileList] :" + fileList);
		return fileList;
	}

	@PostMapping("/update")
	public @ResponseBody CardFileDTO updateCardFile(@RequestBody CardFileDTO cardFileDTO) {
		log.info("[before UPDATE CardFileController cardFileDTO] :" + cardFileDTO);
		cardFileService.updateCardFile(cardFileDTO);
		log.info("[after UPDATE CardFileController cardFileDTO] :" + cardFileDTO);
		return cardFileDTO;
	}

	@PostMapping("/upload")
	public @ResponseBody CardFileDTO handleFileUpload(
			@RequestParam("card_id") int card_id, 
			@RequestParam("file") MultipartFile file) throws JsonProcessingException {
		CardFileDTO cardFileDTO = new CardFileDTO();
		try {
		//cardFileService.store(file);
		log.info("fileupload"+file.getName());
		
		String originalFile = file.getOriginalFilename();
		String fileExtension = originalFile.substring(originalFile.lastIndexOf("\\")+1);
		String storedFileName = UUID.randomUUID().toString().replaceAll("-", "") +"_"+ fileExtension;
		//String filePath = "C:\\sojeong\\workspace\\gotcha_frontend\\public\\upload\\";
		String filePath = "/upload/"+storedFileName;
		File getFile = new File("C:/sojeong/workspace/gotcha_frontend/public"+filePath);
		
		file.transferTo(getFile); //첫 번째 방법
//		try {
//			byte fileData[]=file.getBytes();
//			fos = new FileOutputStream(filePath+file.getOriginalFilename());
//			fos.write(fileData);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			if(fos != null) {
//				fos.close();
//			}
//		}

		log.info("filePath:"+filePath);
		
		cardFileDTO.setFile_id(cardFileService.selectFileId());
		cardFileDTO.setFile_name(storedFileName);
		cardFileDTO.setCard_id(card_id);
		cardFileDTO.setFile_path(filePath);
	
		log.info("[UPLOAD CardFileController cardFileDTO] :"+cardFileDTO);
		cardFileService.insertCardFile(cardFileDTO);
		
		
		}catch(Exception e) {
			e.printStackTrace();
			return null;
	}
			return cardFileDTO;
	}
	
	//@GetMapping
	
	@PostMapping("/delete") //return: CardFileDTO, Service parameter: getFile_id
	public @ResponseBody CardFileDTO deleteCardFile(@RequestBody CardFileDTO cardFileDTO) {
		log.info("[DELETE CardFileController cardFileDTO.getFile_id()] :" + cardFileDTO.getFile_id());
		log.info("[DELETE CardFileController cardFileDTO] :" + cardFileDTO);
		cardFileService.deleteCardFile(cardFileDTO.getFile_id());
		return cardFileDTO;
	}
	
}
