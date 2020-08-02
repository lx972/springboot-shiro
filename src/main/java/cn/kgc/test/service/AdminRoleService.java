package cn.kgc.test.service;

import cn.kgc.test.model.AdminRole;
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
     * 加载所有可用角色
     *
     * @return
     */
    ResultAPI findAllEnabledRole();


 /**
     * 加载所有角色
     *
     * @return
     */
    ResultAPI findAllRole();


    /**
     * 更新角色状态
     *
     * @param role
     * @return
     */
    ResultAPI enabledRole(AdminRole role);


    /**
     * 更新角色信息及其菜单和功能
     *
     * @param role
     * @return
     */
    ResultAPI updateRoleMenuAndPermission(AdminRole role);
}
