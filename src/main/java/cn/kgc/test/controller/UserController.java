package cn.kgc.test.controller;

import cn.kgc.test.model.User;
import cn.kgc.test.service.UserService;
import cn.kgc.test.utils.ResultAPI;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * cn.kgc.test.controller
 *
 * @Author Administrator
 * @date 15:27
 */
@RestController
@RequestMapping(path = "/api")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResultAPI login(@RequestBody User user) {
        System.out.println(user);
        Subject current = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            current.login(token);
            Serializable sessionId = current.getSession().getId();
            return new ResultAPI(200, "登录成功", sessionId);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return new ResultAPI(500, "账号或密码错误！");
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return new ResultAPI(401, "没有权限！");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultAPI(500, "账号或密码错误！");
        }

    }


    /**
     * 验证是否已经登录
     *
     * @return
     */
    @RequestMapping(path = "/authentication", method = RequestMethod.GET)
    public ResultAPI authentication() {
        //已登录才能访问到此方法
        return new ResultAPI(200, "认证成功");
    }

}
