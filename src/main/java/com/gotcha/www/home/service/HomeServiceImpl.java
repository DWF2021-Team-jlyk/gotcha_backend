package com.gotcha.www.home.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotcha.www.home.dao.HomeDAO;
import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;


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
		List<String> wsUserList = homeDAO.selecWsUserList(ws_id);
		return wsUserList;
	}

}
