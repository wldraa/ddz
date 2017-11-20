package cn.wldraa.ddz.game;

/**
 * @author zhangqian
 */
public class BaseRound {

    private SeatLocation location;

    public BaseRound() {
    }

    public BaseRound(SeatLocation location) {
        this.location = location;
    }

    public SeatLocation getLocation() {
        return location;
    }

    public void setLocation(SeatLocation location) {
        this.location = location;
    }
}
