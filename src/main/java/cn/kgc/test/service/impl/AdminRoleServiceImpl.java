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
     * 加载所有角色
     *
     * @param uid
     * @return
     */
    @Override
    public ResultAPI findAllRoleByUid(Integer uid) {
        //查询所有角色详情
        List<AdminRole> adminRoles = adminRoleMapper.selectRolesByUserId(null);
        return new ResultAPI(200, "所有角色详情加载成功", adminRoles);
    }
}
