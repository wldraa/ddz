package cn.wldraa.game.poker.controller;

import cn.wldraa.game.poker.dao.UserDTO;
import cn.wldraa.game.poker.dto.ResultDTO;
import cn.wldraa.game.poker.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangqian
 */

@RestController
@RequestMapping("/room")
public class RoomController extends BaseController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/list")
    public ResultDTO roomList() {
        return doneSuccess(gameService.tableList());
    }

    @RequestMapping("/join")
    public ResultDTO join(Integer tableId, String seatId) {
        gameService.joinTable(getCurrentUser(), tableId, seatId);
        return doneSuccess();
    }

}
