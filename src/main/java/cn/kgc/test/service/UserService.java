package cn.kgc.test.service;


import cn.kgc.test.model.User;

/**
 * cn.kgc.test.service
 *
 * @Author Administrator
 * @date 15:22
 */
public interface UserService {

    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    User getUserByUsernameAndPassword(String username, String password);

}
