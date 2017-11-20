package cn.wldraa.ddz.dto;

import java.util.Objects;

/**
 * @author zhangqian
 */
public class UserDTO {

    private Integer id;

    private String userName;

    private String nickName;

    private String avatar;

    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object user) {
        return user instanceof UserDTO && Objects.equals(id, ((UserDTO) user).id);
    }
}
