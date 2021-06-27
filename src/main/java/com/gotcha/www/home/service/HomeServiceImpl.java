package com.gotcha.www.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gotcha.www.home.dao.HomeDAO;
import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;


@Service
public class HomeServiceImpl implements HomeService{
	
	@Autowired
	HomeDAO homeDAO;

	@Override
	public List<WorkspaceDto> selectWorkspace(String user_id) {
		List<WorkspaceDto> adminList = homeDAO.selectWorkspace(user_id); 
		return adminList;
	}

	@Override
	public List<NotiJoinVO> selectNotice(String user_id) {
		List<NotiJoinVO> notiList = homeDAO.selectNoti(user_id);
		return notiList;
	}

	@Override
	public void updateFav(WorkspaceDto workspaceDto) {
		homeDAO.updateFav(workspaceDto);
	
	}

}
