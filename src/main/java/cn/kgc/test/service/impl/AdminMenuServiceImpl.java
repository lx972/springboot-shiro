package cn.kgc.test.service.impl;

import cn.kgc.test.mapper.AdminMenuMapper;
import cn.kgc.test.mapper.AdminRoleMapper;
import cn.kgc.test.mapper.UserMapper;
import cn.kgc.test.model.AdminMenu;
import cn.kgc.test.model.User;
import cn.kgc.test.service.AdminMenuService;
import cn.kgc.test.utils.ResultAPI;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * cn.kgc.test.service.impl
 *
 * @Author Administrator
 * @date 17:01
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminMenuServiceImpl implements AdminMenuService {

    @Autowired
    private AdminMenuMapper adminMenuMapper;


    @Autowired
    private UserMapper userMapper;


    @Autowired
    private AdminRoleMapper adminRoleMapper;


    /**
     * 根据当前登录的用户查询用户所拥有的菜单
     *
     * @return
     */
    @Override
    public List<AdminMenu> getMenusByCurrentUser() {
        //得到当前登录的用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //根据用户名查询当前用户信息
        User user = new User();
        user.setUsername(username);
        User loginUser = userMapper.selectOneByUser(user);
        //根据用户id查询该用户所拥有的的角色id
        List<Integer> rids = adminRoleMapper.selectRoleIdByUserId(loginUser.getId());
        //根据用户角色查询其所拥有的菜单
        List<AdminMenu> adminMenus = adminMenuMapper.selectMenusByRids(rids);
        //整理菜单信息，形成树结构
        return createTree(adminMenus);
    }


    /**
     * 根据角色id查询所拥有的菜单
     *
     * @return
     */
    @Override
    public List<AdminMenu> findAllMenusByRid(Integer rid) {
        List<Integer> rids = new ArrayList<>();
        rids.add(rid);
        //根据角色查询其所拥有的菜单
        List<AdminMenu> adminMenus = adminMenuMapper.selectMenusByRids(rids);
        //整理菜单信息，形成树结构
        return createTree(adminMenus);
    }

    /**
     * 根据角色id查询拥有的菜单id
     *
     * @return
     */
    @Override
    public ResultAPI findMenuIdsByRid(Integer rid) {
        //根据角色查询其所拥有的菜单id
        List<Integer> menuIds = adminMenuMapper.selectMenuIdsByRid(rid);

        return new ResultAPI(200, "查询角色id拥有的菜单id成功", menuIds);
    }


    /**
     * 查询所有菜单
     *
     * @return
     */
    @Override
    public List<AdminMenu> findAllMenus() {
        List<AdminMenu> adminMenus = adminMenuMapper.selectAll();
        //整理菜单信息，形成树结构
        return createTree(adminMenus);
    }

    /**
     * 整理菜单信息，形成树结构
     *
     * @param adminMenus
     * @return
     */
    public List<AdminMenu> createTree(List<AdminMenu> adminMenus) {
        for (AdminMenu adminMenu : adminMenus) {
            //找到该菜单的所有子菜单
            List<AdminMenu> children = adminMenuMapper.selectMenusByParentId(adminMenu.getId());
            adminMenu.setChildren(children);
        }
        //使用迭代器删除子菜单项
        Iterator<AdminMenu> iterator = adminMenus.iterator();
        while (iterator.hasNext()) {
            AdminMenu menu = iterator.next();
            if (!menu.getParent_id().equals(0)) {
                //子菜单项,删除
                iterator.remove();
            }
        }
        return adminMenus;
    }
}
