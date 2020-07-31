package cn.kgc.test.service.impl;

import cn.kgc.test.mapper.UserMapper;
import cn.kgc.test.model.User;
import cn.kgc.test.service.UserService;
import cn.kgc.test.utils.ResultAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

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


    @Autowired
    private UserMapper userMapper;

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
        String MD5 = DigestUtils.md5DigestAsHex((user.getPassword() + user.getSalt()).getBytes());
        user.setPassword(MD5);
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
}
