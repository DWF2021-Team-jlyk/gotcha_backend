package com.gotcha.www.home.service;

import com.gotcha.www.card.vo.CardTodoDTO;
import com.gotcha.www.extra.vo.BoardVO;
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
    public void makeBoardNoti(BoardVO boardVO, String[] userId) {
        NotiVO notiVO = new NotiVO();
        final String notiDesc = "" +
                "공지 사항입니다. ";
    }

    @Override
    public void makeInviteNoti(WorkspaceDto workspaceDto, List<String> userIdList) {

        NotiVO notiVO = new NotiVO();

        final String notiDesc =
                "초대 : " + workspaceDto.getWs_name() + "\n" +
                        "역할 : " + workspaceDto.getRole_type() + "\n" +
                        "초대 날짜 : " + getNotiTime();

        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_desc(notiDesc);
        notiVO.setWs_id(workspaceDto.getWs_id());
        notiVO.setNoti_type('i');
        notiVO.setNoti_checked('0');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_type_id(workspaceDto.getWs_id());

        notiDAO.insertNoti(notiVO);
        userIdList.forEach(userId->
                notiDAO.insertNotiUser(
                        new NotiUserVO(notiVO.getNoti_id(), userId)
                )
        );

        log.info("Invite Noti 호출");
    }

    @Override
    public void makeTodoNoti(CardTodoDTO cardTodoDTO, String userId) {
        NotiVO notiVO = new NotiVO();
        final String notiDesc = "할당된 업무 : " + cardTodoDTO.getTodo_name() + "\n" +
                "시작 날짜 : " +
                (cardTodoDTO.getTodo_start_date() == null ?
                        "미정" : cardTodoDTO.getTodo_start_date()) + "\n" +
                "종료 날짜 : " +
                (cardTodoDTO.getTodo_start_date() == null ?
                        "미정" : cardTodoDTO.getTodo_start_date()) +
                "\n";

        notiVO.setNoti_id(notiDAO.getNotiId());
        notiVO.setNoti_type('t');
        notiVO.setNoti_time(getNotiTime());
        notiVO.setNoti_checked('0');
        notiVO.setNoti_type_id(cardTodoDTO.getTodo_id());
        notiVO.setNoti_desc(notiDesc);
        notiVO.setWs_id(workListDAO.selectOneCard(cardTodoDTO.getCard_id()).getWs_id());

        notiDAO.insertNoti(notiVO);
        notiDAO.insertNotiUser(new NotiUserVO(notiVO.getNoti_id(), userId));
    }

    @Override
    public void makeCardNoti(CardVO cardVO, String userId) {

        NotiVO notiVO = new NotiVO();

        final String notiDesc = "할당된 업무 : " + cardVO.getCard_name() + "\n" +
                "시작 날짜 : " +
                (cardVO.getCard_start_date() == null ?
                        "미정" : cardVO.getCard_start_date()) + "\n" +
                "종료 날짜 : " +
                (cardVO.getCard_end_date() == null ?
                        "미정" : cardVO.getCard_end_date()) + "\n";

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
}
