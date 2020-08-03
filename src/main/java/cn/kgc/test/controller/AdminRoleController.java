package cn.kgc.test.controller;

import cn.kgc.test.model.AdminRole;
import cn.kgc.test.service.AdminRoleService;
import cn.kgc.test.utils.ResultAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * cn.kgc.test.controller
 *
 * @Author Administrator
 * @date 10:59
 */
@RestController
@RequestMapping(path = "/api")
public class AdminRoleController {
    private Logger logger = LoggerFactory.getLogger(AdminRoleController.class);

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
            ResultAPI result = adminRoleService.findAllRole();
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }

    /**
     * 加载所有可用角色
     *
     * @return
     */
    @RequestMapping(path = "enabled/roles", method = RequestMethod.GET)
    public ResultAPI loadEnabledRoles() {
        try {
            ResultAPI result = adminRoleService.findAllEnabledRole();
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }

    /**
     * 更新角色状态
     *
     * @return
     */
    @RequestMapping(path = "updation/role/enabled", method = RequestMethod.PATCH)
    public ResultAPI enabledRole(@RequestBody AdminRole role) {
        logger.info(role.toString());
        try {
            ResultAPI result = adminRoleService.enabledRole(role);
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }


    /**
     * 更新角色信息及其菜单和功能
     *
     * @return
     */
    @RequestMapping(path = "/roles", method = RequestMethod.PUT)
    public ResultAPI updateRole(@RequestBody AdminRole role) {
        logger.info("更新角色信息及其菜单和功能");
        logger.info(role.toString());
        try {
            ResultAPI result = adminRoleService.updateRoleMenuAndPermission(role);
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }


    /**
     * 添加角色
     *
     * @return
     */
    @RequestMapping(path = "/roles", method = RequestMethod.POST)
    public ResultAPI addRole(@RequestBody AdminRole role) {
        logger.info("添加角色");
        logger.info(role.toString());
        try {
            ResultAPI result = adminRoleService.addRole(role);
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }

    /**
     * 删除角色
     *
     * @return
     */
    @RequestMapping(path = "/roles/{rid}", method = RequestMethod.DELETE)
    public ResultAPI deleteRole(@PathVariable("rid") Integer rid) {
        logger.info("删除角色");
        logger.info(rid.toString());
        try {
            ResultAPI result = adminRoleService.deleteRoleByRid(rid);
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }

    /**
     * 批量删除角色
     *
     * @return
     */
    @RequestMapping(path = "/roles/batch/{rids}", method = RequestMethod.DELETE)
    public ResultAPI deleteBatchRole(@PathVariable("rids") List<Integer> rids) {
        logger.info("批量删除角色");
        logger.info(rids.toString());
        try {
            ResultAPI result = adminRoleService.deleteBatchRoleByRid(rids);
            return result;
        } catch (Exception e) {
            return new ResultAPI(500, "服务器异常");
        }
    }
}
