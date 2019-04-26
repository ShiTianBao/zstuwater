package com.stb.zstuwater.config.interseptor;

import com.stb.zstuwater.common.GlobalConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Component
public class UserLoginInterceptor implements HandlerInterceptor, GlobalConstant {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (null == session.getAttribute(SESSION_ATTR_KEY)) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
