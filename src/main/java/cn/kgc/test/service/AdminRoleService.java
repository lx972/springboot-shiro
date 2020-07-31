package cn.kgc.test.service;

import cn.kgc.test.utils.ResultAPI;

/**
 * cn.kgc.test.service
 *
 * @Author Administrator
 * @date 11:03
 */
public interface AdminRoleService {

    /**
     * 根据用户id加载角色id
     *
     * @param uid
     * @return
     */
    ResultAPI findRoleIdsByUid(Integer uid);


    /**
     * 加载所有角色
     *
     * @param uid
     * @return
     */
    ResultAPI findAllRoleByUid(Integer uid);
}
