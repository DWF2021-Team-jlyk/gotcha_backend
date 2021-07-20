package com.gotcha.www.home.controller;

import com.gotcha.www.card.vo.CardTodoDTO;
import com.gotcha.www.board.vo.BoardVO;
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

import java.util.HashMap;
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

    @PostMapping("/boardNoti")
    public ResponseEntity<?> boardNoti(
            @RequestParam("board")BoardVO boardVO,
            @RequestParam("userList") List<String> userList
    ) {
        try{
            log.info("boardNoti");
            notiService.makeBoardNoti(boardVO, userList);
        } catch (Exception e){
            return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>("Success", HttpStatus.OK);
    }

//    @PostMapping("/inviteNoti")
//    public ResponseEntity<?> inviteNoti(
//            @RequestParam("workspace") WorkspaceDto workspaceDto,
//            @RequestParam("userId") List<String> userId
//    ) {
//        try {
//            log.info("InviteNoti");
//            notiService.makeInviteNoti(workspaceDto, userId);
//
//        } catch (Exception e) {
//            return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity<Object>("Success", HttpStatus.OK);
//    }

//    @PostMapping("/cardNoti")
//    public ResponseEntity<?> cardNoti(
//            @RequestParam("card") CardVO cardVO,
//            @RequestParam("userId") String userId
//    ) {
//        try {
//            log.info("cardNoti");
//            notiService.makeCardNoti(cardVO, userId);
//        } catch (Exception e) {
//            return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
//        }
//        return new ResponseEntity<Object>("Success", HttpStatus.OK);
//    }

    @PostMapping("/todoNoti")
    public ResponseEntity<?> todoNoti(
            @RequestParam("todo") CardTodoDTO todoDTO,
            @RequestParam("userId") String userId
    ) {
        try {
            log.info("todoNoti");
            notiService.makeTodoNoti(todoDTO, userId);
        } catch (Exception e) {
            return new ResponseEntity<Object>("Fail", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Object>("Success", HttpStatus.OK);
    }

    @PostMapping("/deleteNoti")
    public @ResponseBody int deleteNoti(
            @RequestBody HashMap<String, String> map
            ){
        log.info(map);
        int noti_id = Integer.parseInt(map.get("NOTI_ID"));
        notiService.deleteNoti(noti_id);
        log.info(map);
        return noti_id;
    }

    @PostMapping("/toggleNoti")
    public @ResponseBody HashMap<String, String> toggleNoti(
            @RequestBody HashMap<String, String> map
    ){
        log.info(map);
        int noti_id = Integer.parseInt(map.get("NOTI_ID"));
        notiService.toggleNoti(noti_id);
        return map;
    }
}
