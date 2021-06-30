package com.gotcha.www.home.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.home.service.HomeService;
import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.WorkspaceDto;
import com.gotcha.www.home.vo.UserVO;


@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService homeService;

    private Log log = LogFactory.getLog(this.getClass());

    @PostMapping("/wsList")
    public @ResponseBody
    List<WorkspaceDto> selectWorkspace(@RequestBody UserVO userVO) {
        log.info("\nselectWorkspace\n userVo : " + userVO.toString());
        List<WorkspaceDto> mainList = homeService.selectWorkspace(userVO.getUser_id());
        log.debug("\nselectWorkspace\n mainList : " + mainList);
        return mainList;
    }

    @PostMapping("/notiList")
    public @ResponseBody
    List<NotiJoinVO> selectNotice(@RequestBody UserVO userVO) {
//        List<NotiJoinVO> mainList = homeService.selectNotice(userVO.getUser_id());
//        log.info(mainList);
        return null;
    }

    @PostMapping("/favUpdate")
    public @ResponseBody
    void UpdateFav(@RequestBody WorkspaceDto workspaceDto) {
        homeService.updateFav(workspaceDto);
    }

    @PostMapping("/wsUserList")
    public @ResponseBody
    List<String> selectWsUserList(@RequestBody int ws_id)
            throws Exception {
        List<String> wsUserList = homeService.selectWsUserList(ws_id);
        log.info(wsUserList);
        return wsUserList;

    }
}
