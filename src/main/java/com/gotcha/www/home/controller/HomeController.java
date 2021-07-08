package com.gotcha.www.home.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import com.gotcha.www.user.service.PrincipalDetailsService;
import com.gotcha.www.user.vo.PrincipalDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gotcha.www.home.service.HomeService;
import com.gotcha.www.home.vo.NotiJoinVO;
import com.gotcha.www.home.vo.UserVO;
import com.gotcha.www.home.vo.WorkspaceDto;


@RequestMapping("/home")
@RestController
public class HomeController {
    @Autowired
    HomeService homeService;

    private Log log = LogFactory.getLog(this.getClass());

    @PostMapping("/wsList")
    public @ResponseBody
    List<WorkspaceDto> selectWorkspace(@RequestBody UserVO userVO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
    
    	String userId = getLoginUser(principalDetails);

		log.info("userId : " + userId);

        log.info("\nselectWorkspace\n userVo : " + userVO.toString());
        List<WorkspaceDto> mainList = homeService.selectWorkspace(userVO.getUser_id());
        log.debug("\nselectWorkspace\n mainList : " + mainList);
//        log.info("\ngetUsername : "+principalDetails.getUsername());
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
    List<String> selectWsUserList(@RequestBody HashMap<String, String> map)           
    		throws Exception {
    	String ws_id = map.get("ws_id");
        List<String> wsUserList = homeService.selectWsUserList(Integer.parseInt(ws_id));
        log.info(wsUserList);
        return wsUserList;
       }
    
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
}