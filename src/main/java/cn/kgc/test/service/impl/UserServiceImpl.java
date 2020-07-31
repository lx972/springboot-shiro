package cn.kgc.test.service.impl;

import cn.kgc.test.mapper.AdminUserRoleMapper;
import cn.kgc.test.mapper.UserMapper;
import cn.kgc.test.model.AdminUserRole;
import cn.kgc.test.model.User;
import cn.kgc.test.service.UserService;
import cn.kgc.test.utils.ResultAPI;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * cn.kgc.test.service.impl
 *
 * @Author Administrator
 * @date 15:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public ResultAPI register(User user) {
        User register = new User();
        register.setUsername(user.getUsername());
        //去数据库验证用户名是否已经使用
        User user1 = userMapper.selectOneByUser(register);
        if (user1 != null) {
            //用户名已经使用
            return new ResultAPI(520, "用户名已经使用");
        }
        //随机生成字符串作为盐
        user.setSalt(UUID.randomUUID().toString());
        //使用md5对密码进行加密
        Object md5 = new SimpleHash("MD5", user.getPassword(), user.getSalt(), 1);
        user.setPassword(md5.toString());
        //将该账号设置为可用
        user.setEnabled(true);
        userMapper.insert(user);

        return new ResultAPI(200, "用户注册成功");
    }


    /**
     * 加载所有用户信息
     *
     * @return
     */
    @Override
    public List<User> findAllUserInfo() {
        return userMapper.selectAll();
    }


    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    public ResultAPI updateUserInfo(User user) {
        //更新用户信息
        userMapper.updateByPrimaryKey(user);
        //更新用户所拥有的角色
        //删除原来所拥有的角色
        adminUserRoleMapper.deleteByUid(user.getId());
        //添加角色
        AdminUserRole adminUserRole = new AdminUserRole();
        adminUserRole.setUid(user.getId());
        for (Integer rid : user.getRids()) {
            adminUserRole.setRid(rid);
            adminUserRole.setId(null);
            adminUserRoleMapper.insert(adminUserRole);
        }
        return new ResultAPI(200, "更新用户信息成功");
    }


    /**
     * 重设密码
     *
     * @param uid
     * @return
     */
    @Override
    public ResultAPI resetPassword(Integer uid) {
        User user = new User();
        user.setId(uid);
        user.setSalt(UUID.randomUUID().toString());
        Object md5 = new SimpleHash("MD5", "123", user.getSalt(), 1);
        logger.info(md5.toString());
        user.setPassword(md5.toString());
        userMapper.updateByPrimaryKey(user);
        return new ResultAPI(200, "重设密码成功");
    }
}
