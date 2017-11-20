package cn.wldraa.ddz.game;

/**
 * @author zhangqian
 */
public enum  SeatLocation {
    S1,
    S2,
    S3;

    public SeatLocation nextLocation() {
        SeatLocation[] locations = SeatLocation.values();
        return locations[(this.ordinal() + 1) % locations.length];
    }

    public SeatLocation lastLocation() {
        return this.nextLocation().nextLocation();
    }
}
