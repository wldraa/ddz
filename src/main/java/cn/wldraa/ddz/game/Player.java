package cn.wldraa.ddz.game;

import cn.wldraa.ddz.dto.UserDTO;

import java.util.List;

/**
 * @author zhangqian
 */
public class Player {

    private UserDTO user;

    private PlayerRole role;

    private List<Card> cardList;

    public Player() {

    }

    public Player(UserDTO user) {
        this.user = user;
    }

    public void initCard(List<Card> cardList) {
        this.cardList = cardList;
    }

    public void setRole(PlayerRole role) {
        this.role = role;
    }


}
