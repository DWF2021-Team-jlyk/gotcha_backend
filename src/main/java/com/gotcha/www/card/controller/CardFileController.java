package com.gotcha.www.card.controller;

import java.util.HashMap;
import java.util.List;

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
	public ResponseEntity<Object> handleFileUpload(
			@RequestParam("card_id") int card_id, 
			//@RequestParam("file_ischecked")char file_ischecked,
			@RequestParam("file") MultipartFile file) throws JsonProcessingException {
		try {
		cardFileService.store(file);
		log.info("fileupload"+file.getName());
		String fileName = file.getOriginalFilename();
		CardFileDTO cardFileDTO = new CardFileDTO();
		cardFileDTO.setFile_id(cardFileService.selectFileId());
		cardFileDTO.setFile_name(fileName);
		cardFileDTO.setCard_id(card_id);
		//cardFileDTO.setFile_ischecked(file_ischecked);
		log.info("[UPLOAD CardFileController cardFileDTO] :"+cardFileDTO);
		cardFileService.insertCardFile(cardFileDTO);
		
		
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(null,HttpStatus.CONFLICT);
	}

			return new ResponseEntity<Object>("Success",HttpStatus.OK);

//	@PostMapping(value="/upload", consumes = {MediaType.APPLICATION_JSON_VALUE,
//											  MediaType.MULTIPART_FORM_DATA_VALUE})
//	public CardFileDTO handleFileUpload(@RequestPart("cardFileDTO") String cardFileDTO, @RequestPart("file") List<MultipartFile> file) {
//		CardFileDTO cardFileDTOJson= cardFileService.getJson(cardFileDTO, file);
//		log.info("[cardFileDTOJson]:"+cardFileDTOJson);
//		return cardFileDTOJson;
	}
	
//	@RequestMapping("/delete")
//	public @ResponseBody CardFileDTO deleteCardFile(@RequestParam("file_id") int file_id) {
//		cardFileService.deleteCardFile(file_id);
//		return (CardFileDTO) cardFileService;
//		
//	}

//	@RequestMapping("/delete") //return: CardFileDTO, Service parameter: CardFileDTO,  DAO parameter: getFile_id
//	public @ResponseBody CardFileDTO deleteCardFile(@RequestBody CardFileDTO cardFileDTO) {
//		cardFileService.deleteCardFile(cardFileDTO);
//		log.info("[DELETE CardFileController cardFileDTO] :" + cardFileDTO);
//		return cardFileDTO;
//	}
	
//	@PostMapping("/delete") //return: CardFileDTO, Service parameter: getFile_id
//	public @ResponseBody CardFileDTO deleteCardFile(@RequestBody CardFileDTO cardFileDTO) {
//		log.info("[DELETE CardFileController cardFileDTO.getFile_id()] :" + cardFileDTO.getFile_id());
//		cardFileService.deleteCardFile(cardFileDTO.getFile_id());
//		return cardFileDTO;
//	}
//	@GetMapping("/delete/:{fileId}") //return: CardFileDTO, Service parameter: getFile_id
//	public @ResponseBody int deleteCardFile(@PathVariable int fileId) {
//		log.info("[DELETE CardFileController cardFileDTO.getFile_id()] :" + fileId);
//		cardFileService.deleteCardFile(fileId);
//		return fileId;
//	}
//	
	@PostMapping("/delete") //return: List<CardFileDTO>, Service parameter: Integer.parseInt(file_id)
	public @ResponseBody CardFileDTO deleteCardFile(@RequestBody HashMap<String, Object> map) {
		log.info(map);
		map.values().forEach(log::info);
		map.values().forEach(value->log.info(value.getClass()));
		int file_id = (int)map.get("file_id");
		log.info(file_id);
		CardFileDTO dto = new CardFileDTO();
		dto.setFile_id(file_id);
		log.info(dto);
		cardFileService.deleteCardFile(file_id);
		log.info(dto);
//		String file_id = map.get("file_id");
//		log.info("CardFileController delete file_id : " + file_id);
//		log.info("[DELETE CardFileController] :" + deleteFileList);
//		return Integer.parseInt(file_id);
		return dto;
	}

//	@RequestMapping("/delete") //return: List<CardFileDTO>, Service parameter: Integer.parseInt(file_id)
//	public @ResponseBody int deleteCardFile(HttpServletRequest request) {
//		log.info(request.getParameterMap().values());
//		log.info(request.getParameterMap().keySet());
////		String file_id = map.get("file_id");
////		log.info("CardFileController delete file_id : " + file_id);
////		cardFileService.deleteCardFile(Integer.parseInt(file_id)); 
////		log.info("[DELETE CardFileController] :" + deleteFileList);
////		return Integer.parseInt(file_id);
//		return -1;
//	}
	
}
