package com.gotcha.www.home.service;

import com.gotcha.www.card.vo.CardTodoDTO;
import com.gotcha.www.board.vo.BoardVO;
import com.gotcha.www.home.dao.NotiDAO;
import com.gotcha.www.home.vo.NotiUserVO;
import com.gotcha.www.home.vo.NotiVO;
import com.gotcha.www.home.vo.WorkspaceDto;
import com.gotcha.www.workList.dao.WorkListDAO;
import com.gotcha.www.workList.vo.CardVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NotiServiceImpl implements NotiService {

    private final Log log = LogFactory.getLog(this.getClass());
    private final NotiDAO notiDAO;
    private final WorkListDAO workListDAO;

    @Autowired
    public NotiServiceImpl(NotiDAO _notiDAO, WorkListDAO _workListDAO) {
        notiDAO = _notiDAO;
        workListDAO = _workListDAO;
    }

    private String getNotiTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return sdf.format(date);
    }

    @Override
    @Transactional
    public void makeBoardNoti(BoardVO boardVO, List<String> userIdList) {
        NotiVO notiVO = new NotiVO();
        final String notiDesc = "" +
                "등록된 보드 : " + boardVO.getBoard_title() + "\n" +
                "등록일 : " + boardVO.getBoard_reg_time() + "\n";

        log.info(boardVO);

        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_desc(notiDesc);
        notiVO.setWs_id(boardVO.getWs_id());
        notiVO.setNoti_type('b');
        notiVO.setNoti_checked('0');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_type_id(boardVO.getId());

        log.info(notiVO);

        notiDAO.insertNoti(notiVO);
        userIdList.forEach(userId ->
                notiDAO.insertNotiUser(new NotiUserVO(notiVO.getNoti_id(), userId))
        );
    }

    @Override
    @Transactional
    public void makeUpdatedBoardNoti(BoardVO boardVO, List<String> userIdList) {
        NotiVO notiVO = new NotiVO();
        final String notiDesc = "" +
                "수정된 보드 : " + boardVO.getBoard_title() + "\n" +
                "수정일 : " + (boardVO.getBoard_mod_time() != null ?
                boardVO.getBoard_mod_time() : "");

        log.info(boardVO);

        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_desc(notiDesc);
        notiVO.setWs_id(boardVO.getWs_id());
        notiVO.setNoti_type('b');
        notiVO.setNoti_checked('0');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_type_id(boardVO.getId());

        log.info(notiVO);

        notiDAO.insertNoti(notiVO);
        userIdList.forEach(userId ->
                notiDAO.insertNotiUser(new NotiUserVO(notiVO.getNoti_id(), userId))
        );
    }

    @Override
    @Transactional
    public void makeInviteNoti(String ws_name, int ws_id, List<String> userIdList) {

        NotiVO notiVO = new NotiVO();

        final String notiDesc =
                "초대 : " +  ws_name + "\n" +
                        "초대 날짜 : " + getNotiTime();

        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_desc(notiDesc);
        notiVO.setWs_id(ws_id);
        notiVO.setNoti_type('i');
        notiVO.setNoti_checked('0');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_type_id(ws_id);

        notiDAO.insertNoti(notiVO);
        userIdList.forEach(userId ->
                notiDAO.insertNotiUser(new NotiUserVO(notiVO.getNoti_id(), userId))
        );

        log.info("Invite Noti 호출");
    }

    @Override
    @Transactional
    public void makeExpelNoti(String ws_name, int ws_id, String userId, String reason){

        NotiVO notiVO = new NotiVO();

        final String notiDesc = ws_name + "에서 추방 되셨습니다." + "\n" + "이유 : " + reason;

        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_desc(notiDesc);
        notiVO.setWs_id(ws_id);
        notiVO.setNoti_type('i');
        notiVO.setNoti_checked('0');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_type_id(ws_id);

        notiDAO.insertNoti(notiVO);
        notiDAO.insertNotiUser(new NotiUserVO(notiVO.getNoti_id(), userId));
    }

    @Override
    @Transactional
    public void makeTodoNoti(CardTodoDTO cardTodoDTO, String userId) {
        NotiVO notiVO = new NotiVO();
        CardVO cardVO = workListDAO.selectOneCard(cardTodoDTO.getCard_id());
        final String notiDesc =
                "할당된 할일 : " + cardTodoDTO.getTodo_name() + "\n" +
                        "위치한 카드 : " + cardVO.getCard_name() + "\n" +
                        "시작 날짜 : " +
                        (cardTodoDTO.getTodo_start_date() == null ?
                                "미정" : cardTodoDTO.getTodo_start_date().substring(0, 10)) + "\n" +
                        "종료 날짜 : " +
                        (cardTodoDTO.getTodo_start_date() == null ?
                                "미정" : cardTodoDTO.getTodo_start_date().substring(0, 10)) +
                        "\n";

        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_type('t');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_checked('0');
        notiVO.setNoti_type_id(cardTodoDTO.getTodo_id());
        notiVO.setNoti_desc(notiDesc);
        notiVO.setWs_id(cardVO.getWs_id());

        notiDAO.insertNoti(notiVO);
        notiDAO.insertNotiUser(new NotiUserVO(notiVO.getNoti_id(), userId));
    }

    @Override
    @Transactional
    public void makeCardNoti(int cardId, String userId) {

        NotiVO notiVO = new NotiVO();
        CardVO cardVO = workListDAO.selectOneCard(cardId);

        final String notiDesc =
                "할당된 카드 : " + cardVO.getCard_name() + "\n" +
                        "시작 날짜 : " +
                        (cardVO.getCard_start_date() == null ?
                                "미정" : cardVO.getCard_start_date().substring(0, 10)) + "\n" +
                        "종료 날짜 : " +
                        (cardVO.getCard_end_date() == null ?
                                "미정" : cardVO.getCard_end_date().substring(0, 10)) + "\n";

        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_type('c');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_checked('0');
        notiVO.setNoti_type_id(cardVO.getCard_id());
        notiVO.setWs_id(cardVO.getWs_id());
        notiVO.setNoti_desc(notiDesc);

        notiDAO.insertNoti(notiVO);
        notiDAO.insertNotiUser(new NotiUserVO(notiVO.getNoti_id(), userId));
    }

    @Override
    @Transactional
    public void cancelCardNoti(int cardId, String userId) {

        NotiVO notiVO = new NotiVO();
        CardVO cardVO = workListDAO.selectOneCard(cardId);

        final String notiDesc =
                "카드가 취소 되었습니다.\n" +
                        "취소된 카드 : " + cardVO.getCard_name() + "\n";

        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_type('c');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_checked('0');
        notiVO.setNoti_type_id(cardVO.getCard_id());
        notiVO.setWs_id(cardVO.getWs_id());
        notiVO.setNoti_desc(notiDesc);

        notiDAO.insertNoti(notiVO);
        notiDAO.insertNotiUser(new NotiUserVO(notiVO.getNoti_id(), userId));
    }

    @Override
    @Transactional
    public void outNoti(int ws_id, String user_id, String reason){

        List<String> userIdList = notiDAO.getWsMember(ws_id);

        final String notiDesc = "유저 "+user_id+"가 나갔습니다.\n" + "이유 : "+ reason +"\n";

        NotiVO notiVO = new NotiVO();
        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_type('o');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_checked('0');
        notiVO.setNoti_type_id(ws_id);
        notiVO.setWs_id(ws_id);
        notiVO.setNoti_desc(notiDesc);

        notiDAO.insertNoti(notiVO);
        userIdList.forEach(
                user-> notiDAO.insertNotiUser(
                        new NotiUserVO(notiVO.getNoti_id(), user)
                )
        );
    }

    @Override
    @Transactional
    public void mandateNoti(int ws_id, String user_id, String reason){

        List<String> userIdList = notiDAO.getWsMember(ws_id);

        final String notiDesc = "유저 "+user_id+"가 내보내졌습니다.\n" + "이유 : "+ reason +"\n";

        NotiVO notiVO = new NotiVO();
        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_type('o');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_checked('0');
        notiVO.setNoti_type_id(ws_id);
        notiVO.setWs_id(ws_id);
        notiVO.setNoti_desc(notiDesc);

        notiDAO.insertNoti(notiVO);
        userIdList.forEach(
                user-> notiDAO.insertNotiUser(
                        new NotiUserVO(notiVO.getNoti_id(), user)
                )
        );
    }

    @Override
    public void deleteNoti(int notiId) {
        notiDAO.deleteNoti(notiId);
    }

    @Override
    public void toggleNoti(int notiId) {
        notiDAO.changeNotiCheck(notiId);
    }
}
