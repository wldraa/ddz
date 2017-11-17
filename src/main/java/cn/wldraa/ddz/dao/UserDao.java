package cn.wldraa.ddz.dao;

import cn.wldraa.ddz.entity.User;
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
