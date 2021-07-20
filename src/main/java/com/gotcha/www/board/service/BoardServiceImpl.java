package com.gotcha.www.board.service;

import java.util.List;

import com.gotcha.www.home.dao.HomeDAO;
import com.gotcha.www.home.service.NotiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gotcha.www.board.dao.BoardDAO;
import com.gotcha.www.board.vo.BoardVO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	private final BoardDAO boardDAO;
	private final HomeDAO homeDAO;
	private final NotiService notiService;

	
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);

	@Autowired
	public BoardServiceImpl(BoardDAO boardDAO, HomeDAO homeDAO, NotiService notiService) {
		this.boardDAO = boardDAO;
		this.homeDAO = homeDAO;
		this.notiService = notiService;
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
		int ws_id = boardVO.getWs_id();
		log.info("insertBoard" + boardVO.toString());
		boardDAO.insertBoard(boardVO);
		List<String> wsUserList = homeDAO.selectWsUserList(ws_id);
		notiService.makeBoardNoti(boardVO, wsUserList);
	}

	@Override
	public void updateBoard(BoardVO boardVO) {
		int ws_id = boardVO.getWs_id();
		log.info("updateBoard" + boardVO.toString());
		boardDAO.updateBoard(boardVO);
		List<String> wsUserList = homeDAO.selectWsUserList(ws_id);
		notiService.makeUpdatedBoardNoti(boardVO, wsUserList);
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
