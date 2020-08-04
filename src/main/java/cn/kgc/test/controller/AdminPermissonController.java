package cn.kgc.test.controller;

import cn.kgc.test.model.AdminPermission;
import cn.kgc.test.service.AdminPermissionService;
import cn.kgc.test.service.AdminRoleService;
import cn.kgc.test.utils.ResultAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * cn.kgc.test.controller
 *
 * @Author Administrator
 * @date 10:31
 */
@RestController
@RequestMapping(path = "/api")
public class AdminPermissonController {

    @Autowired
    private AdminPermissionService adminPermissionService;

    @Autowired
    private AdminRoleService adminRoleService;


    /**
     * 加载所有的功能
     *
     * @return
     */
    @RequestMapping(path = "/permissions", method = RequestMethod.GET)
    public ResultAPI getAllPermission() {
        try {
            ResultAPI result = adminPermissionService.findAllPermission();
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }


    /**
     * 加载该角色拥有功能的id
     *
     * @return
     */
    @RequestMapping(path = "/permissions/{rid}", method = RequestMethod.GET)
    public ResultAPI getAllPermission(@PathVariable("rid") Integer rid) {
        try {
            ResultAPI result = adminPermissionService.findPidsByRid(rid);
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }


    /**
     * 加载该角色功能表格
     *
     * @return
     */
    @RequestMapping(path = "/permissions/table/{rid}", method = RequestMethod.GET)
    public ResultAPI getTablePermission(@PathVariable("rid") Integer rid) {
        try {
            ResultAPI result = adminPermissionService.findTableByRid(rid);
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }

    /**
     * 更改功能状态
     *
     * @return
     */
    @RequestMapping(path = "update/role/permission/{rid}", method = RequestMethod.PUT)
    public ResultAPI updateTablePermission(@PathVariable("rid") Integer rid,@RequestBody AdminPermission adminPermission) {
        try {
            ResultAPI result = adminRoleService.updateTableByRid(rid,adminPermission);
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }
}
