package cn.kgc.test.controller;

import cn.kgc.test.model.AdminMenu;
import cn.kgc.test.service.AdminMenuService;
import cn.kgc.test.utils.ResultAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * cn.kgc.test.controller
 *
 * @Author Administrator
 * @date 17:53
 */
@RestController
@RequestMapping(path = "/api")
public class AdminMenuController {

    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 加载后台菜单
     *
     * @return
     */
    @RequestMapping(path = "/menus", method = RequestMethod.GET)
    public ResultAPI menus() {
        List<AdminMenu> menusByCurrentUser = adminMenuService.getMenusByCurrentUser();
        if (menusByCurrentUser != null) {
            return new ResultAPI(200, "加载菜单成功", menusByCurrentUser);
        } else {
            return new ResultAPI(404, "该用户无菜单");
        }
    }
}
