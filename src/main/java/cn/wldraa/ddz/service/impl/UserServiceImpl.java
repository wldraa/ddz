package cn.wldraa.ddz.service.impl;

import cn.wldraa.ddz.entity.User;
import cn.wldraa.ddz.util.TokenUtils;
import cn.wldraa.ddz.dto.UserDTO;
import cn.wldraa.ddz.dao.UserDao;
import cn.wldraa.ddz.exception.ServiceException;
import cn.wldraa.ddz.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangqian
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public String login(String userName, String password) {
        User user = userDao.queryByName(userName);
        if (null == user) {
            throw new ServiceException("user_not_exists", "用户不存在");
        }
        if (!password.equals(user.getPassword())) {
            throw new ServiceException("invalid_password", "用户名或密码错误");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return TokenUtils.setUser(userDTO);
    }

}
