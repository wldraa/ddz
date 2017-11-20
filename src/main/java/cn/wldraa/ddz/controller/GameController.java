package cn.wldraa.ddz.controller;

import cn.wldraa.ddz.dto.ResultDTO;
import cn.wldraa.ddz.game.GameStatus;
import cn.wldraa.ddz.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangqian
 */

@RestController
public class GameController extends BaseController {

    @Autowired
    private GameService gameService;

    @RequestMapping("/room/list")
    public ResultDTO roomList() {
        return doneSuccess(gameService.tableList());
    }

    @RequestMapping("/room/join")
    public ResultDTO join(Integer tableId, String seatId) {
        gameService.joinTable(getCurrentUser(), tableId, seatId);
        return doneSuccess();
    }

    @RequestMapping("/game/bid")
    public ResultDTO bid(Boolean isBid) {
        gameService.bid(getCurrentUser(), isBid);
        return doneSuccess();
    }

    @RequestMapping("/game/play")
    public ResultDTO play(List<Integer> cards) {
        gameService.play(getCurrentUser(), cards);
        return doneSuccess();
    }

    @RequestMapping("/game/status")
    public ResultDTO status() {
        GameStatus status = gameService.status(getCurrentUser());
        return doneSuccess(status);
    }
}
