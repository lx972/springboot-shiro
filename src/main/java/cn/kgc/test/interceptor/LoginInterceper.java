package cn.kgc.test.interceptor;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cn.kgc.test.interceptor
 *
 * @Author Administrator
 * @date 10:53
 */
public class LoginInterceper implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //放行option请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())){
            System.out.println("放行option请求");
            return true;
        }
        Subject current = SecurityUtils.getSubject();
        if (!current.isAuthenticated()){
            //认证失败
            System.out.println("认证失败");
            return false;
        }
        System.out.println("认证true");
        return true;
    }

}
