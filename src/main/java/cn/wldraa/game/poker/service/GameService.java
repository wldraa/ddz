package cn.wldraa.game.poker.service;

import cn.wldraa.game.poker.dto.TableDTO;
import cn.wldraa.game.poker.dto.UserDTO;

import java.util.List;

/**
 * @author zhangqian
 */
public interface GameService {
    List<TableDTO> tableList();

    void joinTable(UserDTO user, Integer tableId, String seatId);
}
