package com.gotcha.www.board.service;

import java.util.List;

import com.gotcha.www.board.vo.BoardVO;

public interface BoardService {

	int getBoardId();

	List<BoardVO> selectBoard(int ws_id);

	void insertBoard(BoardVO boardVO);

	void updateBoard(BoardVO baordVO);

	void deleteBoard(int board_id);
}
