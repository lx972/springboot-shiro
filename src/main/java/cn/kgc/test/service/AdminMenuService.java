package cn.kgc.test.service;

import cn.kgc.test.model.AdminMenu;
import cn.kgc.test.utils.ResultAPI;

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


    /**
     * 根据角色id查询拥有的菜单
     *
     * @return
     */
    List<AdminMenu> findAllMenusByRid(Integer rid);

    /**
     * 查询所有菜单
     *
     * @return
     */
    List<AdminMenu> findAllMenus();


    /**
     * 根据角色id查询拥有的菜单id
     *
     * @return
     */
    ResultAPI findMenuIdsByRid(Integer rid);
}
