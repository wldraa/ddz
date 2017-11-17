package cn.wldraa.ddz.game;

import cn.wldraa.ddz.exception.GameException;
import cn.wldraa.ddz.util.MapUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zhangqian
 */
public enum CardStyle {
    single,
    sequence,
    doublee,
    double_sequence,
    triple,
    triple_sequence,
    triple_and_single,
    triple_and_single_sequence,
    triple_and_double,
    triple_and_double_sequence,
    boom(1),
    boom_and_double_single,
    boom_and_double_double,
    rocket(2);

    private int level;

    CardStyle() {
        this(0);
    }

    CardStyle(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean greaterThan(CardStyle cardStyle) {
        return level > cardStyle.level;
    }

    private static Map<String, CardStyle> signatureCardStyleMap;

    private static Map<Pattern, CardStyle> patternCardStyleMap;

    static {
        signatureCardStyleMap = new HashMap<>();
        patternCardStyleMap = new HashMap<>();

        signatureCardStyleMap.put("1", CardStyle.single);
        signatureCardStyleMap.put("2", CardStyle.doublee);
        signatureCardStyleMap.put("3", CardStyle.triple);
        signatureCardStyleMap.put("4", CardStyle.boom);
        signatureCardStyleMap.put("13", CardStyle.triple_and_single);
        signatureCardStyleMap.put("23", CardStyle.triple_and_double);
        signatureCardStyleMap.put("114", CardStyle.boom_and_double_single);
        signatureCardStyleMap.put("224", CardStyle.boom_and_double_double);

        patternCardStyleMap.put(Pattern.compile("1{5,12}"), CardStyle.sequence);
        patternCardStyleMap.put(Pattern.compile("2{3,12}"), CardStyle.double_sequence);
        patternCardStyleMap.put(Pattern.compile("3{2,12}"), CardStyle.triple_sequence);

        // triple_and_single_sequence  triple_and_double_sequence  rocket 另外单独判断
    }


    public static CardStyle getStyle(Collection<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new GameException();
        }
        Map<CardValue, List<Card>> cardMap = MapUtils.groupBy(cards, Card::getValue);
        List<String> numList = cardMap.values().stream().map(v -> String.valueOf(v.size())).collect(Collectors.toList());
        numList.sort(String::compareTo);
        String signature = String.join("", numList);

        if (signatureCardStyleMap.containsKey(signature)) {
            return signatureCardStyleMap.get(signature);
        }

        for (Map.Entry<Pattern, CardStyle> entry : patternCardStyleMap.entrySet()) {
            if (entry.getKey().matcher(signature).matches() && CardValue.isSequence(cardMap.keySet())) {
                return entry.getValue();
            }
        }


        // rocket
        if (cardMap.size() == 2 && cardMap.containsKey(CardValue.JOK) && cardMap.containsKey(CardValue.JOKER)) {
            return CardStyle.rocket;
        }
        // triple_and_single_sequence
        if (signature.length() % 2 == 0) {
            int length = signature.length() / 2;
            String regex = "(1|2){" + length + "}" + "3{" + length + "}";
            Matcher matcher = Pattern.compile(regex).matcher(signature);
            if (matcher.matches()) {
                List<CardValue> seqCardValues = cardMap.entrySet().stream()
                        .filter(entry -> entry.getValue().size() == 3)
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList());
                if (CardValue.isSequence(seqCardValues)) {
                    String match = matcher.group(1);
                    if (match.charAt(0) == '1') {
                        return CardStyle.triple_and_single_sequence;
                    } else {
                        return CardStyle.triple_and_double_sequence;
                    }
                }
            }
        }
        throw new GameException("illegal_card_style", "牌型不符合规范");
    }

}
