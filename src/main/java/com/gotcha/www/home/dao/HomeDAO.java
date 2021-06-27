package com.gotcha.www.home.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;

@Mapper
public interface HomeDAO {

	List<WorkspaceDto> selectWorkspace(String user_id);

	List<NotiJoinVO> selectNoti(String user_id);
	
	void updateFav(WorkspaceDto workspaceDto);

}