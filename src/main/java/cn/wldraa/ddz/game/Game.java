package cn.wldraa.ddz.game;

import cn.wldraa.ddz.exception.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author zhangqian
 */
public class Game {

    private Logger logger = LoggerFactory.getLogger(Game.class);

    /**
     * 所在的桌子
     */
    private Table table;

    /**
     * 玩家
     */
    private Map<SeatLocation, Player> playerMap;

    /**
     * 当前回合轮到
     */
    private SeatLocation nowRound;

    /**
     * 牌堆
     */
    private CardHeap cardHeap;

    /**
     * 底牌
     */
    private List<Card> lastCards;

    /**
     * 当前游戏阶段。准备，叫地主，出牌
     */
    private GameStage stage;

    /**
     * 上一次出的牌
     */
    private CardSuit lastCardSuit;

    /**
     * 上一次出牌（叫牌）玩家
     */
    private SeatLocation location;


    public Game(Table table) {
        this.table = table;
    }

    /**
     * 初始化一个游戏
     */
    public void init() {
        playerMap = table.getPlayers();
        cardHeap = new CardHeap();
        cardHeap.shuffle();

        if (playerMap.size() != 3) {
            logger.error("玩家数量过少 {}", playerMap.values());
            throw new GameException();
        }

        for (Player player : playerMap.values()) {
            player.initCard(cardHeap.getCards(17));
        }
        lastCards = cardHeap.getCards(3);

    }

    public void bid() {

    }

}
