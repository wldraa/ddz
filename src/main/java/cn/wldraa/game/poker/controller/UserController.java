package cn.wldraa.game.poker.controller;

import cn.wldraa.game.poker.dto.ResultDTO;
import cn.wldraa.game.poker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangqian
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public ResultDTO login(String userName, String password) {
        userService.login(userName, password);
        return doneSuccess();
    }
}
