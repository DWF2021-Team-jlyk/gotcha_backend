package com.gotcha.www.board.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.board.service.BoardService;
import com.gotcha.www.board.vo.BoardVO;
import com.gotcha.www.user.vo.PrincipalDetails;


@RestController
@RequestMapping("/board")
public class BoardController {
	private Log log = LogFactory.getLog(this.getClass());

	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	@PostMapping("")
	public @ResponseBody List<BoardVO> selectBoard(@RequestBody HashMap<String, String> map) {
		String ws_id = map.get("ws_id");
		log.info("BoardController ws_id:" + ws_id);
		List<BoardVO> boardList = boardService.selectBoard(Integer.parseInt(ws_id));
		log.info("BoardController after service:" + boardList);
		return boardList;
	}
	
	@PostMapping("/getAllUsers")
    public @ResponseBody String selectUserList(@RequestBody BoardVO boardVO,
    		@AuthenticationPrincipal PrincipalDetails principalDetails){
    	String userId = getLoginUser(principalDetails);
    	return userId;
	}
    
    // get login id
    public String getLoginUser(PrincipalDetails principalDetails) {
    	String userId="";
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof PrincipalDetails) {
			userId = ((PrincipalDetails) principal).getUsername();
		} else {
			userId = principal.toString();
		}
		return userId;
    }


	@RequestMapping("/insert")
	public @ResponseBody BoardVO insertBoard(@RequestBody BoardVO boardVO) {
		log.info("controller, service before:" + boardVO);
		boardVO.setId(boardService.getBoardId());
		log.info("boardService.getBoardId()"+boardVO.getId());
		boardService.insertBoard(boardVO);
		log.info("controller, service after:" + boardVO);
		return boardVO;
	}

	@RequestMapping("/update")
	public @ResponseBody BoardVO updateBoard(@RequestBody BoardVO boardVO) {
		log.info("updateboard"+boardVO);
		boardService.updateBoard(boardVO);
		return boardVO;
	}

	@RequestMapping("/delete")
	public @ResponseBody BoardVO deleteBoard(@RequestBody BoardVO baordVO) {
		log.info("delete controller, service befor" + baordVO);
		boardService.deleteBoard(baordVO.getId());
		log.info("delete controller, service after getTodo_id()" + baordVO.getId());
		log.info("delete controller, service after" + baordVO);
		return baordVO;
	}
	
	@RequestMapping("/showPost")
	public @ResponseBody BoardVO selectBoardById(@RequestBody BoardVO boardVO) {
		log.info("BoardController id:" + boardVO);
		
		boardVO = boardService.selectBoardById(boardVO.getId());
		log.info("BoardController after service:" + boardVO);
		return boardVO;

	}

}
