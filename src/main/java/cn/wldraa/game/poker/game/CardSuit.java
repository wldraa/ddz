package cn.wldraa.game.poker.game;

import java.util.*;

/**
 * @author zhangqian
 */
public class CardSuit {

    private List<Card> cardList;

    private CardValue value;

    private CardStyle cardStyle;

    public CardSuit(Collection<Card> cards) {
        cardList = new ArrayList<>(cards);
        cardStyle = CardStyle.getStyle(cards);
        value = CardValue.getValue(cards);
    }


    public boolean greaterThan(CardSuit cardSuit) {
        return cardStyle.greaterThan(cardSuit.cardStyle) ||
                (cardStyle == cardSuit.cardStyle && cardList.size() == cardSuit.cardList.size() && value.greaterThan(cardSuit.value));
    }

}
