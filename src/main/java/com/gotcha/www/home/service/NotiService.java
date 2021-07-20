package com.gotcha.www.home.service;

import com.gotcha.www.card.vo.CardTodoDTO;
import com.gotcha.www.board.vo.BoardVO;
import com.gotcha.www.home.vo.NotiVO;
import com.gotcha.www.home.vo.WorkspaceDto;
import com.gotcha.www.workList.vo.CardVO;

import java.util.List;

public interface NotiService {
    void makeBoardNoti(BoardVO boardVO, List<String> userId);
    void makeInviteNoti(String ws_name, int ws_id, List<String> userIdList);
    void makeExpelNoti(String ws_name, int ws_id, String userId, String reason);
    void makeTodoNoti(CardTodoDTO cardTodoDTO, String userId);
    void makeCardNoti(int cardId, String userId);
    void makeUpdatedBoardNoti(BoardVO boardVO, List<String> userId);
    void cancelCardNoti(int cardId, String userId);
    void deleteNoti(int notiId);
    void toggleNoti(int notiId);
    void outNoti(int ws_id, String user_id, String reason);
    void mandateNoti(int ws_id, String user_id, String reason);
}