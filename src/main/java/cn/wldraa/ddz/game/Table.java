package cn.wldraa.ddz.game;

import cn.wldraa.ddz.dto.UserDTO;
import cn.wldraa.ddz.exception.GameException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangqian
 */
public class Table {

    private Map<SeatLocation, Player> players;

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


    public void ready(SeatLocation location) {
        Player player = players.get(location);
        if (player == null) {
            throw new GameException("玩家不存在");
        }
        player.setReady(true);

        if (isAllPlayerReady()) {
            startGame();
        }
    }

    private boolean isAllPlayerReady() {
        for(SeatLocation location : SeatLocation.values()) {
            Player player = players.get(location);
            if (player == null || !player.getReady()) {
                return false;
            }
        }
        return true;
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

    public synchronized void bid(SeatLocation location, Boolean isBid) {
        game.bid(location, isBid);
    }

    public synchronized void play(SeatLocation location, List<Card> cardList) {
        game.play(location, cardList);
    }

    public synchronized Game status(SeatLocation location) {
        return game;
    }

}
