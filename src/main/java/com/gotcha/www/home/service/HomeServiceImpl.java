package com.gotcha.www.home.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gotcha.www.home.dao.HomeDAO;
import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;
import com.gotcha.www.user.file.UploadFileUtil;


@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	HomeDAO homeDAO;

	private Log log = LogFactory.getLog(this.getClass());

	@Override
	public List<WorkspaceDto> selectWorkspace(String user_id) {
		List<WorkspaceDto> adminList = homeDAO.selectWorkspace(user_id); 
		return adminList;
	}

	@Override
	public List<NotiJoinVO> selectNotice(String user_id) {
		List<NotiJoinVO> notiList = homeDAO.selectNoti(user_id);
		log.info(notiList);
//		log.info(notiList.getClass());
//		log.info(notiList.get(0).getClass());
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
	public boolean fileUpload(String ws_name, String fileName, byte[] fileByte) {
		FileOutputStream out = null;
		try {
			int lastIndex = homeDAO.getWsLastIndex();
			log.info("[lastIndex] " + lastIndex);
			String uploadFolder = "C:\\gotcha\\workspaces\\" + lastIndex + "\\bg";
			log.info("[uploadFolder] " + uploadFolder);
			// 폴더 생성
			UploadFileUtil.mkdirDir(uploadFolder);
			// 파일 업로드
			UploadFileUtil.fileUpload(uploadFolder, fileName, fileByte);
			
//			File saveFile = new File(uploadFolder, file.getOriginalFilename());
//			file.transferTo(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally {
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		return true;
	}
	
	@Transactional
	@Override
	public void createWorkspace(String user_id, String ws_name, String originalFilename) {
		
		int nextIndex = homeDAO.getWsLastIndex();
		log.info("[LAST INDEX] " + nextIndex);
		
		HashMap<String, Object> workspaceMap = new HashMap<String, Object>(); 
		workspaceMap.put("ws_id", nextIndex);
		workspaceMap.put("ws_name", ws_name);
		workspaceMap.put("ws_isImage", originalFilename);
		homeDAO.createWorkspace(workspaceMap);
		
		HashMap<String, Object> userRoleMap = new HashMap<String, Object>();
		userRoleMap.put("user_id", user_id);
		userRoleMap.put("ws_id", nextIndex);
		homeDAO.createUserRole(userRoleMap);
		
	}
	
}
