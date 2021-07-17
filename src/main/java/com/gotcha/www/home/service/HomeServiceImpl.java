package com.gotcha.www.home.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import com.gotcha.www.home.dao.NotiDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.home.dao.HomeDAO;
import com.gotcha.www.home.vo.CardFileVO;
import com.gotcha.www.home.vo.InviteMemberVO;
import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;
import com.gotcha.www.user.file.UploadFileUtil;


@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	HomeDAO homeDAO;

	@Autowired
	NotiService notiService;

	@Value("${service.file.uploadurl}")
	private String fileUploadPath;
	
	@Value("${service.card.uploadurl}")
	private String fileDeletePath;
	
//	@Autowired
//    public HomeServiceImpl(FileUploadProperties fileUploadProperties) {
//        this.dirLocation = Paths.get(fileUploadProperties.getLocation())
//                .toAbsolutePath().normalize();
//    }

//    @PostConstruct
//    public void init() {
//        try {
//            Files.createDirectories(this.dirLocation);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
	
	private Log log = LogFactory.getLog(this.getClass());

	private Path dirLocation = null;
	
	@Override
	public List<WorkspaceDto> selectWorkspace(String user_id) {
		List<WorkspaceDto> adminList = homeDAO.selectWorkspace(user_id); 
		return adminList;
	}

	@Override
	public List<NotiJoinVO> selectNotice(String user_id) {
		List<NotiJoinVO> notiList = homeDAO.selectNoti(user_id);
		log.info(notiList);
		return notiList;
	}

	@Override
	public void updateFav(WorkspaceDto workspaceDto) {
		homeDAO.updateFav(workspaceDto);
	
	}

	@Override
	public List<String> selectWsUserList(int ws_id) {
		List<String> wsUserList = homeDAO.selectWsUserList(ws_id);
		return wsUserList;
	}

	@Override
	public boolean fileUpload(String ws_name, String newFileName, byte[] fileByte) {

		try {
			int lastIndex = homeDAO.getWsLastIndex();
			log.info("[lastIndex] " + lastIndex);
			String uploadFolder = fileUploadPath + lastIndex + "/bg";

//			String uploadFolder = "resources/static/upload" + lastIndex + "/bg";
//			System.out.println("[foldername]"+uploadFolder);
//			String uploadFolder = "C:\\gotcha\\workspaces\\" + lastIndex + "\\bg";
//			Path location = this.dirLocation.resolve(fileName);
//			log.info("[uploadFolder] " + location);
//			Files.copy(multipartFile.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);

			// 폴더 생성
			UploadFileUtil.mkdirDir(uploadFolder);
			// 파일 업로드
			UploadFileUtil.fileUpload(uploadFolder, null, newFileName, fileByte);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// add workspace
	@Transactional
	@Override
	public void createWorkspace(String user_id, String ws_name, String originalFilename, List<String> member_id) {
		
		int nextIndex = homeDAO.getWsNextIndex();
		log.info("[LAST INDEX] " + nextIndex);
		
		HashMap<String, Object> workspaceMap = new HashMap<String, Object>(); 
		workspaceMap.put("ws_id", nextIndex);
		workspaceMap.put("ws_name", ws_name);
		workspaceMap.put("ws_isImage", originalFilename);
		homeDAO.createWorkspace(workspaceMap);
		
		HashMap<String, Object> roleAdminMap = new HashMap<String, Object>();
		roleAdminMap.put("user_id", user_id);
		roleAdminMap.put("ws_id", nextIndex);
		homeDAO.setRoleAdmin(roleAdminMap);
		
		HashMap<String, Object> roleMemberMap = null;
		for(String member : member_id) {
			roleMemberMap = new HashMap<String, Object>();
			roleMemberMap.put("user_id", member);
			roleMemberMap.put("ws_id", nextIndex);
			homeDAO.setRoleMember(roleMemberMap);
		}
		notiService.makeInviteNoti(ws_name, nextIndex, member_id);
	}
	
	@Override
	public void updateWsName(WorkspaceDto workspaceDto) {
		homeDAO.updateWsName(workspaceDto);
	}

	@Override
	public boolean fileUpdate(int ws_id, String newFileName, byte[] fileByte) {
		String uploadFolder = fileUploadPath + ws_id + "/bg";
		try {
			String preFileName = homeDAO.preFileName(ws_id);
			log.info("[PRE FILE NAME]"+preFileName);
			UploadFileUtil.mkdirDir(uploadFolder);
			if(preFileName == null) {
				UploadFileUtil.fileUpload(uploadFolder, null, newFileName, fileByte);
			}else if(preFileName != null) {
				UploadFileUtil.fileUpload(uploadFolder, preFileName, newFileName, fileByte);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public void updateFileName(int ws_id, String fileName) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ws_id", ws_id);
		map.put("ws_isImage", fileName);
		homeDAO.updateFileName(map);
	}

	@Override
	public String getFileName(int ws_id) {
		return homeDAO.preFileName(ws_id);
	}

	@Override
	public void addMember(InviteMemberVO inviteMemberVO) {
		String ws_id = Integer.toString(inviteMemberVO.getWs_id());
		inviteMemberVO.getEmailList().forEach(email -> {
			homeDAO.addMember(ws_id, email);
		});
		inviteMemberVO.getEmailList().forEach(log::info);
		notiService.makeInviteNoti(
				homeDAO.getWsNameById(inviteMemberVO.getWs_id()),
				inviteMemberVO.getWs_id(),
				inviteMemberVO.getEmailList()
		);
	}

	@Override
	public void deleteMember(HashMap<String, Object> map) {
		homeDAO.deleteMember(map);
	}
	
	// search user
	public List<String> getAllUserId(InviteMemberVO inviteMemberVO) {
		return homeDAO.getAllUserId(inviteMemberVO);
	}

	@Override
	@Transactional
	public void leaveWorkspace(int ws_id, String user_id, String userId) {
		
		HashMap<String,Object> mandateMap = new HashMap<String,Object>();
		mandateMap.put("ws_id", ws_id);
		mandateMap.put("user_id", user_id);
		
		homeDAO.mandateAdmin(mandateMap);
		log.info("[SUCCESS MANDATE]");
		HashMap<String,Object> deleteMap = new HashMap<String,Object>();
		deleteMap.put("ws_id", ws_id);
		deleteMap.put("user_id", userId);
		
		homeDAO.deleteCardMember(deleteMap);
		log.info("[SUCCESS deleteCardMember]");
		homeDAO.deleteMember(deleteMap);
		log.info("[SUCCESS deleteUserRole]");
		
		homeDAO.deleteFav(deleteMap);
	}

	@Override
	@Transactional
	public void deleteWorkspace(int ws_id, String userId) throws IOException {
		
		HashMap<String,Object> deleteMap = new HashMap<String,Object>();
		deleteMap.put("ws_id", ws_id);
		deleteMap.put("user_id", userId);
		log.info("[DELETE REQUEST] " + ws_id);
		
		// 카드 관련 테이블 삭제
		homeDAO.deleteTodos(deleteMap);
		
		List<CardFileVO> fileInfo = homeDAO.selectFiles(deleteMap);
		fileInfo.forEach(index -> {
			if(index.getFile_path() != null) {				
				UploadFileUtil.fileDelete(fileDeletePath, index.getFile_name());
				log.info("[FILE INFO] " + index);
			}
		});
		log.info("[CARD FILE VO] " + fileInfo);
		
		homeDAO.deleteFiles(deleteMap);
		
		homeDAO.deleteCardMember(deleteMap);
		homeDAO.deleteCards(deleteMap);
		log.info("[CARD TABLES DELETE SUCCESS]");
		
		// 리스트 테이블 삭제
		homeDAO.deleteLists(deleteMap);
		log.info("[LIST TABLES DELETE SUCCESS]");
		
		// 게시판 삭제
		homeDAO.deleteBoard(deleteMap);
		log.info("[BOARD TABLES DELETE SUCCESS]");
		
		// user role 삭제
		homeDAO.deleteMember(deleteMap);
		homeDAO.deleteFav(deleteMap);
		log.info("[USER TABLES DELETE SUCCESS]");
		
		// 이미지 파일 삭제
		String uploadFolder = fileUploadPath + ws_id + "/bg";
		String preFileName = homeDAO.preFileName(ws_id);
		UploadFileUtil.fileDelete(uploadFolder, preFileName);
		log.info("[WORKSPACE IMAGE DELETE SUCCESS]");
		
		// 워크스페이스 삭제
		homeDAO.deleteWorkspace(deleteMap);
		log.info("[WORKSPACE TABLES DELETE SUCCESS]");
	}
	
	/**
	 * 파일 업로드
	 * @param file
	 */
//	@Override
//	public void store(MultipartFile file) {
//		String filename = StringUtils.cleanPath(file.getOriginalFilename());
//		log.info("[SERVICE STORE] " + file.getOriginalFilename());
//		try {
//			
//
//			InputStream inputStream = file.getInputStream();
//			Files.copy(inputStream, getPath().resolve(filename),
//				StandardCopyOption.REPLACE_EXISTING);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	/**
	 * 업로드 폴더 없을 경우 생성
	 */
//	public void init() {
//		try {
//			Files.createDirectories(getPath());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 패스 객체 반환
	 * @return
	 */
//	private Path getPath() {
//		return Paths.get(fileUploadPath);
//	}

}
