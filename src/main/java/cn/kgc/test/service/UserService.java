package cn.kgc.test.service;


import cn.kgc.test.model.User;
import cn.kgc.test.utils.ResultAPI;

import java.util.List;

/**
 * cn.kgc.test.service
 *
 * @Author Administrator
 * @date 15:22
 */
public interface UserService {

    /**
     * 注册
     *
     * @param user
     * @return
     */
    ResultAPI register(User user);


    /**
     * 加载所有用户信息
     *
     * @return
     */
    List<User> findAllUserInfo();


    /**
     * 更新用户信息
     *
     * @param user
     * @return
     */
    ResultAPI updateUserInfo(User user);


    /**
     * 重设密码
     *
     * @param uid
     * @return
     */
    ResultAPI resetPassword(Integer uid);


    /**
     * 修改账户状态
     *
     * @param user
     * @return
     */
    ResultAPI updataEnabled(User user);


    /**
     * 删除账户
     *
     * @param uid
     * @return
     */
    ResultAPI deleteUser(Integer uid);

    /**
     * 批量删除用户
     *
     * @param uids
     * @return
     */
    ResultAPI deleteBatch(List<Integer> uids);


}
