package cn.wldraa.game.poker.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * 牌堆，一副牌
 * @author zhangqian
 */
public class CardHeap {

    /**
     * 所有牌的集合
     */
    private List<Card> heap;

    /**
     * 当前发的位置
     */
    private int index = 0;

    /**
     * 初始化一副牌
     */
    public CardHeap() {
        heap = new ArrayList<>(54);
        for (int i = 0 ; i < 54 ; i++) {
            heap.add(new Card(i));
        }
    }

    /**
     * 洗牌
     */
    public void shuffle() {
        Random random = new Random();
        for (int i = 0 ; i < heap.size() ; i++) {
            Collections.swap(heap, i, random.nextInt(heap.size()));
        }
        index = 0;
    }

    /**
     * 获取下一张牌
     */
    public Card getCard() {
        return heap.get(index++);
    }

    /**
     * 获取下 n 张牌
     */
    public List<Card> getCards(int length) {
        List<Card> result = heap.subList(index, index + length);
        index += length;
        return result;
    }

}
