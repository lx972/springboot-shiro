package cn.kgc.test.service.impl;

import cn.kgc.test.mapper.UserMapper;
import cn.kgc.test.model.User;
import cn.kgc.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userMapper.selectOneByUser(user);
    }
}
