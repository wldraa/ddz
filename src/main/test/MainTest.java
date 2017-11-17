
import cn.wldraa.ddz.game.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangqian
 */
public class MainTest {

    @Test
    public void test() {

        SeatLocation location = SeatLocation.valueOf("s1");
        System.out.println(location);

        List<Card> cardList = new ArrayList<>();
//        cardList.add( new Card(CardValue.JOKER, CardColor.JOKER) );
        cardList.add( new Card(CardValue.V3, CardColor.SPADE) );
        cardList.add( new Card(CardValue.V3, CardColor.SPADE) );
        cardList.add( new Card(CardValue.V4, CardColor.SPADE) );
        cardList.add( new Card(CardValue.V4, CardColor.SPADE) );
        cardList.add( new Card(CardValue.V4, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.V10, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.VJ, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.VK, CardColor.SPADE) );
        cardList.add( new Card(CardValue.V3, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.V9, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.VQ, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.V8, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.V4, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.V4, CardColor.SPADE) );
        cardList.add( new Card(CardValue.V7, CardColor.SPADE) );
        cardList.add( new Card(CardValue.V7, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.V2, CardColor.SPADE) );
        cardList.add( new Card(CardValue.V6, CardColor.SPADE) );
        cardList.add( new Card(CardValue.V6, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.V2, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.V2, CardColor.SPADE) );
//        cardList.add( new Card(CardValue.V2, CardColor.SPADE) );
        CardStyle style = CardStyle.getStyle(cardList);
        System.out.println(style);
    }
}
