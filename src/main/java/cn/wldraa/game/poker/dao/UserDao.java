package cn.wldraa.game.poker.dao;

import cn.wldraa.game.poker.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangqian
 */
@Repository
public interface UserDao {

    List<User> queryList();

    User queryByName(String name);

}
