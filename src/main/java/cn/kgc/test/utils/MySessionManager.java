package cn.kgc.test.utils;


import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 定义sessionid的获取方式
 *
 * @Author Administrator
 * @date 16:22
 */
public class MySessionManager extends DefaultWebSessionManager {

    /**
     * 请求头此段信息存放sessionid
     */
    private static final String AUTHORIZATION = "authorization";


    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";


    public MySessionManager() {
        super();
    }


    /**
     * 不使用从cookie中获取sessionid的方式
     * 而是使用从请求头中获取sessionid
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //得到请求头中的sessionid信息
        String sessionid = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (!StringUtils.isEmpty(sessionid)) {
            //存在sessionid,已登录
            System.out.println(sessionid);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, sessionid);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return sessionid;
        } else {
            //不存在，禁止使用cookie中的sessionid
            //return null;
            return super.getSessionId(request, response);
        }
    }
}
