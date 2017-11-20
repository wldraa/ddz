package cn.wldraa.ddz.service.impl;

import cn.wldraa.ddz.exception.GameException;
import cn.wldraa.ddz.game.*;
import cn.wldraa.ddz.service.GameService;
import cn.wldraa.ddz.dto.TableDTO;
import cn.wldraa.ddz.dto.UserDTO;
import cn.wldraa.ddz.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public void bid(UserDTO user, Boolean isBid) {
        Room.Position position = getPosition(user);
        position.getTable().bid(position.getSeatLocation(), isBid);
    }

    @Override
    public void play(UserDTO user, List<Integer> cardIds) {
        List<Card> cardList = cardIds.stream().map(Card::new).collect(Collectors.toList());
        Room.Position position = getPosition(user);
        position.getTable().play(position.getSeatLocation(), cardList);
    }

    @Override
    public GameStatus status(UserDTO user) {
        Room.Position position = getPosition(user);
        return position.getTable().status(position.getSeatLocation());
    }

    private Room.Position getPosition(UserDTO user) {
        Room.Position position = room.findPosition(user);
        if (position == null) {
            throw new GameException("先找个位置坐下来吧");
        }
        return position;
    }

}
