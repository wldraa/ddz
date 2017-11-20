package cn.wldraa.ddz.game;

/**
 * @author zhangqian
 */
public class PlayRound extends BaseRound {

    private CardSuit cardSuit;

    public PlayRound(SeatLocation location, CardSuit cardSuit) {
        super(location);
        this.cardSuit = cardSuit;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public void setCardSuit(CardSuit cardSuit) {
        this.cardSuit = cardSuit;
    }
}
