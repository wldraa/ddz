package cn.wldraa.game.poker.dto;

/**
 * @author zhangqian
 */
public class TableDTO {

    private boolean s1;

    private boolean s2;

    private boolean s3;

    public TableDTO(boolean s1, boolean s2, boolean s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public boolean isS1() {
        return s1;
    }

    public void setS1(boolean s1) {
        this.s1 = s1;
    }

    public boolean isS2() {
        return s2;
    }

    public void setS2(boolean s2) {
        this.s2 = s2;
    }

    public boolean isS3() {
        return s3;
    }

    public void setS3(boolean s3) {
        this.s3 = s3;
    }
}
