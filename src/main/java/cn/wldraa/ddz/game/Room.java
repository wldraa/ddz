package cn.wldraa.ddz.game;

import cn.wldraa.ddz.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangqian
 */

public class Room {

    public static final Integer MAX_ROOM_SIZE = 100;

    private List<Table> tableList;

    public Room() {
        tableList = new ArrayList<>(MAX_ROOM_SIZE);
        for (int i = 0 ; i < MAX_ROOM_SIZE ; i++) {
            tableList.add(new Table());
        }
    }

    public List<Table> getTableList() {
        return tableList;
    }

    public void join(UserDTO userDTO, Integer tableId, SeatLocation location) {
        tableList.get(tableId).addPlayer(userDTO, location);
    }
}
