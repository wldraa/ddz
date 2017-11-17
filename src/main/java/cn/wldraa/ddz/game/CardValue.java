package cn.wldraa.ddz.game;

import cn.wldraa.ddz.util.MapUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 牌的面值
 * @author zhangqian
 */
public enum CardValue {
    V3(3),
    V4(4),
    V5(5),
    V6(6),
    V7(7),
    V8(8),
    V9(9),
    V10(10),
    VJ(11),
    VQ(12),
    VK(13),
    VA(14),
    V2(15, false),
    JOK(16, false),
    JOKER(17, false);

    /**
     * 面值，决定能否压过
     */
    private int value;

    /**
     * 是否能组成顺子
     */
    private boolean canSequence;

    CardValue(int value) {
        this(value, true);
    }

    CardValue(int value, boolean canSequence) {
        this.value = value;
        this.canSequence = canSequence;
    }

    public int getValue() {
        return value;
    }

    public boolean greaterThan(CardValue cardValue) {
        return value > cardValue.value;
    }

    public boolean isCanSequence() {
        return canSequence;
    }

    public CardValue getNextValue() {
        if (this == JOKER) {
            return V3;
        }
        return values()[this.ordinal() + 1];
    }

    public static boolean isSequence(Collection<CardValue> values) {
        List<CardValue> valueList = new ArrayList<>(values);
        valueList.sort((o1, o2) -> o1.ordinal() - o2.ordinal());
        CardValue lastValue = null;
        for (CardValue value : valueList) {
            if (!value.isCanSequence() || (lastValue != null && lastValue.getNextValue() != value)) {
                return false;
            }
            lastValue = value;
        }
        return true;
    }

    public static CardValue getValue(Collection<Card> cards) {
        Map<CardValue, List<Card>> cardMap = MapUtils.groupBy(cards, Card::getValue);
        int maxLength = 0;
        CardValue maxValue = V3;
        for (Map.Entry<CardValue, List<Card>> entry : cardMap.entrySet()) {
            if (entry.getValue().size() > maxLength) {
                maxValue = entry.getKey();
            } else if (entry.getValue().size() == maxLength) {
                maxValue = maxValue.value > entry.getKey().value ? maxValue : entry.getKey();
            }
        }
        return maxValue;
    }
}
