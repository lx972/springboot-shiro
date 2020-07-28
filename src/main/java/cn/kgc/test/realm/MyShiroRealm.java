package cn.kgc.test.realm;

import cn.kgc.test.mapper.UserMapper;
import cn.kgc.test.model.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * cn.kgc.test.realm
 *
 * @Author Administrator
 * @date 10:14
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 身份验证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //先判断用户名是否存在
        if (null == authenticationToken.getPrincipal()) {
            return null;
        }
        //得到登录的用户名
        String username = authenticationToken.getPrincipal().toString();
        User user = new User();
        user.setUsername(username);
        //取数据库找到对应的用户数据
        User loginUser = userMapper.selectOneByUser(user);
        if (null == loginUser) {
            return null;
        } else {
            //进行认证
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, loginUser.getPassword(), getName());
            return simpleAuthenticationInfo;
        }
    }
}
