package cn.kgc.test.controller;

import cn.kgc.test.service.AdminRoleService;
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
 * @date 10:59
 */
@RestController
@RequestMapping(path = "/api")
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 根据用户id加载角色id
     *
     * @param uid
     * @return
     */
    @RequestMapping(path = "/roleId/{uid}", method = RequestMethod.GET)
    public ResultAPI loadRoleId(@PathVariable(name = "uid") Integer uid) {
        try {
            ResultAPI result = adminRoleService.findRoleIdsByUid(uid);
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }

    /**
     * 加载所有角色
     *
     * @return
     */
    @RequestMapping(path = "/roles", method = RequestMethod.GET)
    public ResultAPI loadRoles() {
        try {
            ResultAPI result = adminRoleService.findAllRoleByUid(null);
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }
}
