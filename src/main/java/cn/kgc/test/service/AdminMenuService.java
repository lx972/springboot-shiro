package cn.kgc.test.service;

import cn.kgc.test.model.AdminMenu;

import java.util.List;

/**
 * cn.kgc.test.service
 *
 * @Author Administrator
 * @date 17:00
 */
public interface AdminMenuService {


    /**
     * 根据当前登录的用户查询用户所拥有的菜单
     *
     * @return
     */
    List<AdminMenu> getMenusByCurrentUser();
}
