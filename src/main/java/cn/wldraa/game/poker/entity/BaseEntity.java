package cn.wldraa.game.poker.entity;

import cn.wldraa.game.poker.util.StringUtils;

/**
 * @author zhangqian
 */
public class BaseEntity {

    @Override
    public String toString() {
        return StringUtils.dump(this);
    }
}
