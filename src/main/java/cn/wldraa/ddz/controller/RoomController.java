package cn.wldraa.ddz.controller;

import cn.wldraa.ddz.dto.ResultDTO;
import cn.wldraa.ddz.service.GameService;
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
