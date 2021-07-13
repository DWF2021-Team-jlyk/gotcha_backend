package com.gotcha.www.home.controller;

import com.gotcha.www.card.vo.CardTodoDTO;
import com.gotcha.www.home.service.NotiService;
import com.gotcha.www.home.vo.WorkspaceDto;
import com.gotcha.www.workList.vo.CardVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/noti")
public class NotiController {

    private final Log log = LogFactory.getLog(this.getClass());
    private final NotiService notiService;

    @Autowired
    public NotiController(NotiService _notiService) {
        notiService = _notiService;
    }

    @PostMapping("/inviteNoti")
    public ResponseEntity<?> inviteNoti(
            @RequestParam("workspaceDTO") WorkspaceDto workspaceDto,
            @RequestParam("userId") List<String> userId
    ) {
        try {
            log.info("InviteNoti");
            notiService.makeInviteNoti(workspaceDto, userId);

        } catch (Exception e) {
            return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>("Success", HttpStatus.OK);
    }

    @PostMapping("/cardNoti")
    public ResponseEntity<?> cardNoti(
            @RequestParam("cardVO") CardVO cardVO,
            @RequestParam("userId") String userId
    ) {
        try {
            log.info("cardNoti");
            notiService.makeCardNoti(cardVO, userId);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>("Success", HttpStatus.OK);
    }

    @PostMapping("/todoNoti")
    public ResponseEntity<?> todoNoti(
            @RequestParam("/todoVO") CardTodoDTO todoDTO,
            @RequestParam("/userId") String userId
    ) {
        try {
            log.info("todoNoti");
            notiService.makeTodoNoti(todoDTO, userId);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>("Success", HttpStatus.OK);
    }
}
