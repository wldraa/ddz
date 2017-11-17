package cn.wldraa.ddz.util;

import cn.wldraa.ddz.dto.UserDTO;
import com.alibaba.fastjson.JSON;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zhangqian
 */
public class TokenUtils {

    public static final String CURRENT_USER = "CURRENT_USER";

    public static final String REDIS_PREFIX = "USER:";

    public static final long SESSION_EXPIRE_TIME = 600;

    private static JedisPool jedisPool = (JedisPool) ContextUtils.getBean("jedisPool");

    public static UserDTO getUserByToken(String token) {
        Jedis jedis = jedisPool.getResource();
        String userInfo = jedis.get(REDIS_PREFIX + token);
        return JSON.parseObject(userInfo, UserDTO.class);
    }

    public static String setUser(UserDTO user) {
        Jedis jedis = jedisPool.getResource();
        int retry = 0;
        String token = generateToken(retry);
        String res = jedis.set(REDIS_PREFIX + token, JSON.toJSONString(user), "NX", "EX", SESSION_EXPIRE_TIME);
        while (!"OK".equalsIgnoreCase(res)) {
            token = generateToken(++retry);
            res = jedis.set(REDIS_PREFIX + token, JSON.toJSONString(user), "NX", "EX", SESSION_EXPIRE_TIME);
        }
        return token;
    }

    public static String generateToken(int retry) {
        return StringUtils.generatorRandomString(StringUtils.DICTIONARY_LOWER_LETTER_AND_NUM, 7 + retry);
    }

}
