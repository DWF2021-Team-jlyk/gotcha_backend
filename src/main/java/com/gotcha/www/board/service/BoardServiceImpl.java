package com.gotcha.www.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.board.dao.BoardDAO;
import com.gotcha.www.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	private final BoardDAO boardDAO;

	
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);

	public BoardServiceImpl(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public int getBoardId() {
		return boardDAO.getBoardId();
	}

	@Override
	public List<BoardVO> selectBoard(int ws_id) {
		return boardDAO.selectBoard(ws_id);
	}

	@Override
	public void insertBoard(BoardVO boardVO) {
		boardDAO.insertBoard(boardVO);

	}

	@Override
	public void updateBoard(BoardVO baordVO) {
		boardDAO.updateBoard(baordVO);

	}

	@Override
	public void deleteBoard(int id) {
		boardDAO.deleteBoard(id);
	}

	@Override
	public BoardVO selectBoardById(int id) {
		return boardDAO.selectBoardById(id);
	}
}
