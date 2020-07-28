package cn.kgc.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


/**
 * cn.kgc.test.utils
 *
 * @Author Administrator
 * @date 12:00
 */
public class AuthFilter extends FormAuthenticationFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 拦截所有未登录的请求
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        System.out.println("拦截所有未登录的请求");

        //判断是否是登录url
        if (this.isLoginRequest(request, response)) {
            //判断该登录url是否为post请求
            if (this.isLoginSubmission(request, response)) {
                //去登陆
                return this.executeLogin(request, response);
            } else {

                return true;
            }
        } else {
            //非登录的url全部拦截
            //this.saveRequestAndRedirectToLogin(request, response);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            //设置该请求允许跨域访问
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
            httpResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpResponse.getWriter();
            Map<String, Object> map = new HashMap<>();
            map.put("code", 301);
            //map.put("msg","没有登录，请先登录");
            String s = objectMapper.writeValueAsString(map);
            out.println(s);
            out.flush();
            out.close();
            return false;
        }
    }

    /**
     * 判断哪些请求允许访问
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //放行option请求
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        if (HttpMethod.OPTIONS.toString().equals(httpServletRequest.getMethod())) {
            System.out.println("放行option请求");
            return true;
        }
        //已登录用户请求允许访问，否则去onAccessDenied方法
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
