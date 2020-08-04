package cn.kgc.test.service.impl;

import cn.kgc.test.mapper.AdminPermissionMapper;
import cn.kgc.test.model.AdminPermission;
import cn.kgc.test.service.AdminPermissionService;
import cn.kgc.test.utils.ResultAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * cn.kgc.test.service.impl
 *
 * @Author Administrator
 * @date 10:32
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    /**
     * 加载所有的功能
     *
     * @return
     */
    @Override
    public ResultAPI findAllPermission() {
        List<AdminPermission> adminPermissions = adminPermissionMapper.selectAll();
        return new ResultAPI(200, "加载所有的功能成功", adminPermissions);
    }


    /**
     * 根据角色id查询出该角色拥有的功能id
     *
     * @param rid
     * @return
     */
    @Override
    public ResultAPI findPidsByRid(Integer rid) {
        List<Integer> pids = adminPermissionMapper.selectPidsByRid(rid);
        return new ResultAPI(200, "根据角色id查询出该角色拥有的功能id成功", pids);
    }

    /**
     * 加载该角色功能表格
     *
     * @param rid
     * @return
     */
    @Override
    public ResultAPI findTableByRid(Integer rid) {
        List<AdminPermission> adminPermissions = adminPermissionMapper.selectAll();
        List<Integer> pids = adminPermissionMapper.selectPidsByRid(rid);

        for (int i = 0; i < adminPermissions.size(); i++) {
            adminPermissions.get(i).setStatus(false);
            for (Integer pid : pids) {
                if (adminPermissions.get(i).getId().equals(pid)) {
                    adminPermissions.get(i).setStatus(true);
                }
            }
        }
        return new ResultAPI(200, "根据角色id查询出该角色功能列表成功", adminPermissions);
    }


}
