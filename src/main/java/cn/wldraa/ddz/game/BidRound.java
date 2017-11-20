package cn.wldraa.ddz.game;

/**
 * @author zhangqian
 */
public class BidRound extends BaseRound {

    private Boolean isBid;

    public BidRound() {
    }

    public BidRound(SeatLocation location, Boolean isBid) {
        super(location);
        this.isBid = isBid;
    }

    public Boolean getBid() {
        return isBid;
    }

    public void setBid(Boolean bid) {
        isBid = bid;
    }
}
