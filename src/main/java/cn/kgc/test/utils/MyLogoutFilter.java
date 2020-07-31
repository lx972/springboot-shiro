package cn.kgc.test.utils;

import org.apache.shiro.session.SessionException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Locale;

/**
 * cn.kgc.test.utils
 *
 * @Author Administrator
 * @date 9:56
 */
public class MyLogoutFilter extends LogoutFilter {
    private Logger log= LoggerFactory.getLogger(MyLogoutFilter.class);

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.info("登出方法拦截");
        Subject subject = this.getSubject(request, response);
        if (this.isPostOnlyLogout() && !WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
            return this.onLogoutRequestNotAPost(request, response);
        } else {
            //不需要重定向
            //String redirectUrl = this.getRedirectUrl(request, response, subject);
            log.info("不需要重定向");
            try {
                subject.logout();
                log.info("登出");
            } catch (SessionException var6) {
                log.info("登出异常");
                log.debug("Encountered session exception during logout.  This can generally safely be ignored.", var6);
            }
            return true;
        }
    }
}
