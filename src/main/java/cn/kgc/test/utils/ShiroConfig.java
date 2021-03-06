package cn.kgc.test.utils;

import cn.kgc.test.realm.MyShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Map;

/**
 * cn.kgc.test.utils
 *
 * @Author Administrator
 * @date 10:06
 */
@Configuration
@AutoConfigureAfter(ShiroLifecycleBeanPostProcessorConfig.class)
@ConfigurationProperties(prefix = "shiro")
public class ShiroConfig {
    private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
    /**
     * 凭证匹配器
     * md5加密一次
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义验证放式
     *
     * @return
     */
    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        //将凭证加入容器
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
     * 自己的获取token放式
     *
     * @return
     */
    @Bean
    public SessionManager sessionManager() {
        MySessionManager mySessionManager = new MySessionManager();
        return mySessionManager;
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //设置自己的验证放式
        defaultWebSecurityManager.setRealm(myShiroRealm());
        defaultWebSecurityManager.setSessionManager(sessionManager());
        return defaultWebSecurityManager;
    }

    /**
     * 设置过滤条件
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        //设置自定义认真方式
        filters.put("authc", new AuthcFilter());
        filters.put("logout", new MyLogoutFilter());
        filters.put("perms", new PermsFilter());
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //Map<String, String> map = new HashMap<>();
        Map<String, String> map = permissionMapUtils().generatePermissionMap();
        //配置不需要身份认证的链接
        map.put("/api/login", "anon");
        //登出
        map.put("/api/logout", "logout");
        //其他链接均需认证
        map.put("/**", "authc");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + map);
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 得到权限权限操作的工具类
     *
     * @return
     */
    @Bean
    public PermissionMapUtils permissionMapUtils() {
        PermissionMapUtils permissionMapUtils = new PermissionMapUtils();
        return permissionMapUtils;
    }
}
