package com.gotcha.www.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gotcha.www.board.vo.BoardVO;

@Mapper
public interface BoardDAO {
	int getBoardId();

	List<BoardVO> selectBoard(int ws_id);

	void insertBoard(BoardVO boardVO);

	void updateBoard(BoardVO baordVO);

	void deleteBoard(int id);
	
	BoardVO selectBoardById(int id);
}
