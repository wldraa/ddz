package cn.wldraa.ddz.service;

import cn.wldraa.ddz.dto.TableDTO;
import cn.wldraa.ddz.dto.UserDTO;
import cn.wldraa.ddz.game.Game;

import java.util.List;

/**
 * @author zhangqian
 */
public interface GameService {
    List<TableDTO> tableList();

    void joinTable(UserDTO user, Integer tableId, String seatId);

    void ready(UserDTO user);

    void bid(UserDTO user, Boolean isBid);

    void play(UserDTO user, List<Integer> cardIds);

    Game gameStatus(UserDTO user);
}
