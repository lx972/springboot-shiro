package cn.kgc.test.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * cn.kgc.test.utils
 *
 * @Author Administrator
 * @date 10:00
 */
public class PermsFilter extends PermissionsAuthorizationFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 没有权限的时候进入此方法
     *
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //设置该请求允许跨域访问
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtil.getOrigin());
        httpResponse.setCharacterEncoding("UTF-8");
        PrintWriter out = httpResponse.getWriter();
        Map<String, Object> map = new HashMap<>();

        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            map.put("code", 302);
            map.put("msg","您还没有登录，请先登录吧");
        }else {
            map.put("code", 301);
            map.put("msg","您没有权限，无法访问");
        }
        String s = objectMapper.writeValueAsString(map);
        out.println(s);
        out.flush();
        out.close();
        return false;
    }
}
