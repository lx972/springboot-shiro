package cn.kgc.test.controller;

import cn.kgc.test.service.AdminPermissionService;
import cn.kgc.test.utils.ResultAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
