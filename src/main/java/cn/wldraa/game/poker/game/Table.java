package cn.wldraa.game.poker.game;

import cn.wldraa.game.poker.dto.UserDTO;
import cn.wldraa.game.poker.exception.GameException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangqian
 */
public class Table {

    private Map<SeatLocation, Player> players;

    private Map<Integer, Player> d;

    private Game game;

    public Table() {
        players = new HashMap<>(3);
    }

    public synchronized void addPlayer(UserDTO user, SeatLocation location) {
        if (players.containsKey(location)) {
            throw new GameException("seat_not_empty", "座位已被抢");
        }
        players.put(location, new Player(user));
    }

    public synchronized Game startGame() {
        if (players.size() < 3) {
            throw new GameException("player_too_little", "玩家过少");
        }
        game = new Game(this);
        game.init();
        return game;
    }

    public synchronized Map<SeatLocation, Player> getPlayers() {
        return players;
    }

}
