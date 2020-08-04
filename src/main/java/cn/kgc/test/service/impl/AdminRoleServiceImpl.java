package cn.kgc.test.service.impl;

import cn.kgc.test.mapper.AdminRoleMapper;
import cn.kgc.test.mapper.AdminRoleMenuMapper;
import cn.kgc.test.mapper.AdminRolePermissionMapper;
import cn.kgc.test.mapper.AdminUserRoleMapper;
import cn.kgc.test.model.AdminPermission;
import cn.kgc.test.model.AdminRole;
import cn.kgc.test.model.AdminRoleMenu;
import cn.kgc.test.model.AdminRolePermission;
import cn.kgc.test.service.AdminRoleService;
import cn.kgc.test.utils.ResultAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * cn.kgc.test.service.impl
 *
 * @Author Administrator
 * @date 11:03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private AdminRoleMenuMapper adminRoleMenuMapper;

    @Autowired
    private AdminRolePermissionMapper adminRolePermissionMapper;

    /**
     * 根据用户id加载角色id
     *
     * @param uid
     * @return
     */
    @Override
    public ResultAPI findRoleIdsByUid(Integer uid) {
        //查询当前用户所拥有的角色id
        List<Integer> roleIds = adminRoleMapper.selectRoleIdByUserId(uid);
        return new ResultAPI(200, "当前用户所拥有的角色id加载成功", roleIds);
    }


    /**
     * 加载所有可用角色
     *
     * @return
     */
    @Override
    public ResultAPI findAllEnabledRole() {
        //查询所有可用角色详情
        List<AdminRole> adminRoles = adminRoleMapper.selectAllEnabledRole();
        return new ResultAPI(200, "所有可用角色详情加载成功", adminRoles);
    }

    /**
     * 加载所有角色
     *
     * @return
     */
    @Override
    public ResultAPI findAllRole() {
        //查询所有角色详情
        List<AdminRole> adminRoles = adminRoleMapper.selectAll();
        return new ResultAPI(200, "所有角色详情加载成功", adminRoles);
    }


    /**
     * 更新角色状态
     *
     * @param role
     * @return
     */
    @Override
    public ResultAPI enabledRole(AdminRole role) {
        adminRoleMapper.updateByPrimaryKey(role);
        return new ResultAPI(200, "更新角色状态成功");
    }

    /**
     * 更新角色信息及其菜单和功能
     *
     * @param role
     * @return
     */
    @Override
    public ResultAPI updateRoleMenuAndPermission(AdminRole role) {
        //更新角色相关信息
        adminRoleMapper.updateByPrimaryKey(role);
        //删除角色拥有的菜单
        adminRoleMenuMapper.deleteByRid(role.getId());
        //加入新菜单
        AdminRoleMenu adminRoleMenu = new AdminRoleMenu();
        adminRoleMenu.setRid(role.getId());
        for (Integer mid : role.getMids()) {
            adminRoleMenu.setMid(mid);
            adminRoleMenuMapper.insert(adminRoleMenu);
            adminRoleMenu.setId(null);
        }
        /*//删除角色拥有的功能
        adminRolePermissionMapper.deleteByRid(role.getId());
        //加入新功能
        AdminRolePermission adminRolePermission = new AdminRolePermission();
        adminRolePermission.setRid(role.getId());
        for (Integer pid : role.getPids()) {
            adminRolePermission.setPid(pid);
            adminRolePermissionMapper.insert(adminRolePermission);
            adminRolePermission.setId(null);
        }*/
        return new ResultAPI(200, "更新角色信息及其菜单和功能成功");
    }

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Override
    public ResultAPI addRole(AdminRole role) {
        role.setEnabled(true);
        adminRoleMapper.insert(role);
        return new ResultAPI(200, "添加角色成功");
    }

    /**
     * 删除角色
     *
     * @param rid
     * @return
     */
    @Override
    public ResultAPI deleteRoleByRid(Integer rid) {
        adminRoleMapper.deleteByPrimaryKey(rid);
        //删除角色拥有的菜单
        adminRoleMenuMapper.deleteByRid(rid);
        //删除角色拥有的功能
        adminRolePermissionMapper.deleteByRid(rid);
        return new ResultAPI(200, "删除角色成功");
    }


    /**
     * 批量删除角色
     *
     * @param rids
     * @return
     */
    @Override
    public ResultAPI deleteBatchRoleByRid(List<Integer> rids) {
        adminRoleMapper.deleteBatch(rids);
        for (Integer rid : rids) {
            //删除角色拥有的菜单
            adminRoleMenuMapper.deleteByRid(rid);
            //删除角色拥有的功能
            adminRolePermissionMapper.deleteByRid(rid);
        }
        return new ResultAPI(200, "批量删除角色成功");
    }

    /**
     * 更改功能状态
     *
     * @param rid
     * @return
     */
    @Override
    public ResultAPI updateTableByRid(Integer rid, AdminPermission adminPermission) {
        if (adminPermission.getStatus()){
            //加入功能
            AdminRolePermission adminRolePermission=new AdminRolePermission();
            adminRolePermission.setPid(adminPermission.getId());
            adminRolePermission.setRid(rid);
            adminRolePermissionMapper.insert(adminRolePermission);
        }else {
            //删除功能
            adminRolePermissionMapper.deleteByRidAndPid(rid, adminPermission.getId());
        }
        return new ResultAPI(200, "修改功能状态成功");
    }
}
