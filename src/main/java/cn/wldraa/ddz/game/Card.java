package cn.wldraa.ddz.game;

/**
 * 牌，表示一张牌
 * @author zhangqian
 */
public class Card {

    /**
     * id 牌的id
     */
    private Integer id;

    /**
     * 面值 3,4,10,J,A,JOKER...
     */
    private CardValue value;

    /**
     * 花色 heart spade
     */
    private CardColor color;

    /**
     * 根据 id 创建牌
     */
    public Card(int id) {
        this.id = id;
        if (id < 0 || id > 53) {
            throw new IndexOutOfBoundsException(String.valueOf(id));
        }
        if (id > 51) {
            color = CardColor.JOKER;
            value = id == 52 ? CardValue.JOK : CardValue.JOKER;
        } else {
            color = CardColor.values()[id / 13];
            value = CardValue.values()[id % 13];
        }
    }

    /**
     * 根据 面值和花色 创建牌
     */
    public Card(CardValue value, CardColor color) {
        this.value = value;
        this.color = color;
        if (color == CardColor.JOKER) {
            id = value == CardValue.JOK ? 53 : 54;
        }
        this.id = color.ordinal() * 4 + value.ordinal();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CardValue getValue() {
        return value;
    }

    public void setValue(CardValue value) {
        this.value = value;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }
}
