package cn.wldraa.ddz.game;

import cn.wldraa.ddz.dto.UserDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangqian
 */

public class Room {

    public static final Integer MAX_ROOM_SIZE = 100;

    private List<Table> tableList;

    private Map<UserDTO, Position> positionMap = new HashMap<>(MAX_ROOM_SIZE * SeatLocation.values().length);

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
        Table table = tableList.get(tableId);
        table.addPlayer(userDTO, location);
        positionMap.put(userDTO, new Position(location, table));
    }

    public void leave(UserDTO userDTO) {
        positionMap.remove(userDTO);
    }

    public Position findPosition(UserDTO userDTO) {
        return positionMap.get(userDTO);
    }

    public class Position {

        private SeatLocation seatLocation;

        private Table table;

        Position(SeatLocation seatLocation, Table table) {
            this.seatLocation = seatLocation;
            this.table = table;
        }

        public SeatLocation getSeatLocation() {
            return seatLocation;
        }

        public void setSeatLocation(SeatLocation seatLocation) {
            this.seatLocation = seatLocation;
        }

        public Table getTable() {
            return table;
        }

        public void setTableId(Table table) {
            this.table = table;
        }
    }
}
