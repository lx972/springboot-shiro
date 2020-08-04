package cn.kgc.test.service;

import cn.kgc.test.utils.ResultAPI;

/**
 * cn.kgc.test.service
 *
 * @Author Administrator
 * @date 10:32
 */
public interface AdminPermissionService {

    /**
     * 加载所有的功能
     *
     * @return
     */
    ResultAPI findAllPermission();


    /**
     * 根据角色id查询出该角色拥有的功能id
     *
     * @return
     */
    ResultAPI findPidsByRid(Integer rid);

    /**
     * 加载该角色功能表格
     *
     * @return
     */
    ResultAPI findTableByRid(Integer rid);



}
