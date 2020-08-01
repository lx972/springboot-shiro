package cn.kgc.test.service.impl;

import cn.kgc.test.mapper.AdminRoleMapper;
import cn.kgc.test.mapper.AdminUserRoleMapper;
import cn.kgc.test.model.AdminRole;
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
}
