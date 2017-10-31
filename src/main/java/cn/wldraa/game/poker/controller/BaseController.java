package cn.wldraa.game.poker.controller;

import cn.wldraa.game.poker.dao.UserDTO;
import cn.wldraa.game.poker.dto.ResultDTO;
import cn.wldraa.game.poker.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * @author zhangqian
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler
    public ResultDTO errorHandle(Exception e) {
        logger.error("exception:", e);
        if (e instanceof ServiceException) {
            return doneError(((ServiceException) e).getCode(), e.getMessage());
        }
        return doneError("error", "服务器错误");
    }

    protected ResultDTO doneSuccess() {
        return done(true, "ok", "成功", null);
    }

    protected ResultDTO doneSuccess(Object o) {
        return done(true, "ok", "成功", o);
    }

    protected ResultDTO doneError(String errorCode, String message) {
        return done(false, errorCode, message, null);
    }

    protected ResultDTO done(boolean success, String errorCode, String message, Object o) {
        return new ResultDTO<>(success, errorCode, message, o);
    }

    protected UserDTO getCurrentUser() {
        Object o = RequestContextHolder.getRequestAttributes().getAttribute("CURRENT_USER", RequestAttributes.SCOPE_REQUEST);
        if (null == o) {
            throw new ServiceException("session_expired", "登陆失效，请重新登陆");
        }
        return (UserDTO) o;
    }

}
