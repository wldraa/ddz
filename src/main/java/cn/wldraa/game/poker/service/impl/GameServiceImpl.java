package cn.wldraa.game.poker.service.impl;

import cn.wldraa.game.poker.dao.TableDTO;
import cn.wldraa.game.poker.dao.UserDTO;
import cn.wldraa.game.poker.exception.ServiceException;
import cn.wldraa.game.poker.game.Player;
import cn.wldraa.game.poker.game.Room;
import cn.wldraa.game.poker.game.SeatLocation;
import cn.wldraa.game.poker.game.Table;
import cn.wldraa.game.poker.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangqian
 */

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private Room room;

    @Override
    public List<TableDTO> tableList() {
        List<TableDTO> result = new ArrayList<>(Room.MAX_ROOM_SIZE);
        for (Table table : room.getTableList()) {
            Map<SeatLocation, Player> players = table.getPlayers();
            result.add(new TableDTO(players.containsKey(SeatLocation.S1), players.containsKey(SeatLocation.S2), players.containsKey(SeatLocation.S3)));
        }
        return result;
    }

    @Override
    public void joinTable(UserDTO user, Integer tableId, String seatId) {
        SeatLocation location;
        try {
            location = SeatLocation.valueOf(seatId);
        } catch (IllegalArgumentException e) {
            throw new ServiceException("seat_not_exists", "所选座位不存在", e);
        }
        if (tableId < 0 || tableId >= Room.MAX_ROOM_SIZE) {
            throw new ServiceException("table_not_exists", "所选桌子不存在");
        }
        room.join(user, tableId, location);
    }
}
