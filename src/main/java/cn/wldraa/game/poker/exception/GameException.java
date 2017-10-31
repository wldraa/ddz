package cn.wldraa.game.poker.exception;

/**
 * @author zhangqian
 */
public class GameException extends ServiceException {

    public GameException() {
    }

    public GameException(String message) {
        super(message);
    }

    public GameException(String code, String message) {
        super(code, message);
    }

    public GameException(String code, String message, Throwable e) {
        super(code, message, e);
    }
}
