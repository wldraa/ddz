package cn.wldraa.game.poker.interceptor;

import cn.wldraa.game.poker.dto.UserDTO;
import cn.wldraa.game.poker.exception.ServiceException;
import cn.wldraa.game.poker.util.TokenUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangqian
 */
public class UserLoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        UserDTO user = TokenUtils.getUserByToken(token);
        if (user == null) {
            throw new ServiceException("session_expired", "登陆失效，请重新登陆");
        }
        request.setAttribute(TokenUtils.CURRENT_USER, user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
