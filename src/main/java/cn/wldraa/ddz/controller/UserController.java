package cn.wldraa.ddz.controller;

import cn.wldraa.ddz.dto.ResultDTO;
import cn.wldraa.ddz.service.UserService;
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
        String token = userService.login(userName, password);
        return doneSuccess(token);
    }

    @RequestMapping("/info")
    public ResultDTO info() {
        return doneSuccess(getCurrentUser());
    }
}
