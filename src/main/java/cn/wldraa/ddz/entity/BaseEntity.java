package cn.wldraa.ddz.entity;

import cn.wldraa.ddz.util.StringUtils;

/**
 * @author zhangqian
 */
public class BaseEntity {

    @Override
    public String toString() {
        return StringUtils.dump(this);
    }
}
