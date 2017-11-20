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

    private Boolean ready;

    public Player() {

    }

    public Player(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PlayerRole getRole() {
        return role;
    }

    public void setRole(PlayerRole role) {
        this.role = role;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }
}
