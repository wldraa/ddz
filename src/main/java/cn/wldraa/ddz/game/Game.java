package cn.wldraa.ddz.game;

import cn.wldraa.ddz.exception.GameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author zhangqian
 */
public class Game extends GameStatus {

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
     * 回合记录
     */
    private List<BidRound> bidRoundList;

    /**
     * 打牌记录
     */
    private List<PlayRound> playRoundList;

    /**
     * 当前回合轮到
     */
    private SeatLocation nowRound;


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
    private SeatLocation lastLocation;


    public Game(Table table) {
        this.table = table;
        stage = GameStage.READY;
    }

    /**
     * 初始化一个游戏
     */
    public void init() {
        playerMap = table.getPlayers();

        CardHeap cardHeap = new CardHeap();
        cardHeap.shuffle();

        if (playerMap.size() != 3) {
            logger.error("玩家数量过少 {}", playerMap.values());
            throw new GameException();
        }

        for (Player player : playerMap.values()) {
            player.setCardList(cardHeap.getCards(17));
        }
        lastCards = cardHeap.getCards(3);
        stage = GameStage.BID;

        SeatLocation[] locations = SeatLocation.values();
        nowRound = locations[new Random().nextInt(locations.length)];

        bidRoundList = new ArrayList<>();
        playRoundList = new ArrayList<>();
    }

    public void bid(SeatLocation location, Boolean isBid) {
        if (stage != GameStage.BID) {
            throw new GameException("当前不在叫牌阶段");
        }
        if (location != nowRound) {
            throw new GameException("当前不是该用户的回合");
        }
        bidRoundList.add(new BidRound(location, isBid));
        if (isBid) {
            lastLocation = location;
        }
        if (bidRoundList.size() < 3) {
            // 一圈以内直接下一个叫
            nowRound = nowRound.nextLocation();
        } else if (bidRoundList.size() == 3) {
            // 一圈之后由第一个叫的人继续叫
            List<BidRound> isBidRoundList = bidRoundList.stream().filter(BidRound::getBid).collect(Collectors.toList());
            if (isBidRoundList.isEmpty()) {
                // 没有人叫牌  不叫 不叫 不叫
                throw new GameException("nobody_bid", "没有人叫牌");
            } else if (isBidRoundList.size() == 1) {
                // 只有一个人叫牌 不叫 叫地主 不叫
                startGame();
            } else {
                nowRound = isBidRoundList.get(0).getLocation();
            }
        } else {
            // 最后一次叫牌
            startGame();
        }
    }

    public void startGame() {
        nowRound = lastLocation;

        Player landlordPlayer = playerMap.get(lastLocation);
        landlordPlayer.setRole(PlayerRole.landlord);
        landlordPlayer.getCardList().addAll(lastCards);

        lastCardSuit = null;
    }

    public void play(SeatLocation location, List<Card> cardList) {
        if (stage != GameStage.GAME) {
            throw new GameException("当前不在出牌阶段");
        }
        if (location != nowRound) {
            throw new GameException("当前不是该用户的回合");
        }
        Player player = playerMap.get(location);
        if (cardList == null || cardList.isEmpty()) {
            // 不要
            if (location == nowRound) {
                throw new GameException("不能不出牌");
            }
        } else if (!player.getCardList().containsAll(cardList)) {
            throw new GameException("不能出当前牌");
        } else {
            CardSuit cardSuit = new CardSuit(cardList);
            if (lastLocation != nowRound && !cardSuit.greaterThan(lastCardSuit)) {
                throw new GameException("您的牌没有大过上家");
            }

            playRoundList.add(new PlayRound(location, cardSuit));
            lastCardSuit = cardSuit;
            lastLocation = nowRound;

            player.getCardList().removeAll(cardList);
            if (player.getCardList().isEmpty()) {
                gameOver();
            }
        }
        nowRound = nowRound.nextLocation();
    }

    public void gameOver() {

    }
}
