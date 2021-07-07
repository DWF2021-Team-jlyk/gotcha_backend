package com.gotcha.www.home.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HomeDAO <T>{

	List<WorkspaceDto> selectWorkspace(String user_id);

	List<NotiJoinVO> selectNoti(String user_id);
	
	void updateFav(WorkspaceDto workspaceDto);
	
	List<String> selecWsUserList(int ws_id);

	@Select("Select user_id from GC_USER")
	List<String> getAllUserId();
}
