package cn.wldraa.ddz.exception;

/**
 * @author zhangqian
 */
public class ServiceException extends RuntimeException {

    private String code;

    public ServiceException() {
        this("服务器错误");
    }

    public ServiceException(String message) {
        this("error", message);
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
