package com.lsf.controller;

import com.github.pagehelper.PageInfo;
import com.lsf.domain.ResponseResult;
import com.lsf.domain.Role;
import com.lsf.domain.User;
import com.lsf.domain.UserVo;
import com.lsf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author LSF
 * 2022-03-07 22:13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {
        PageInfo<User> userByPage = userService.findAllUserByPage(userVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "success", userByPage);
        return responseResult;
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam Integer id, @RequestParam String status) {
        if ("ENABLE".equalsIgnoreCase(status)) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }
        userService.updateUserStatus(id, status);
        ResponseResult responseResult = new ResponseResult(true, 200, "success", status);
        return responseResult;
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {
        User login = userService.login(user);

        if (login != null) {
            //保存用户id及access_token到session中
            String access_token = UUID.randomUUID().toString();
            Integer id = login.getId();

            HttpSession session = request.getSession();
            session.setAttribute("user_id", id);
            session.setAttribute("access_token", access_token);

            HashMap<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id", id);

            return new ResponseResult(true, 200, "登录成功", map);
        } else {
            return new ResponseResult(true, 200, "用户名或密码错误", null);
        }

    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(@RequestParam Integer id) {
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true, 200, "success", roleList);
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {
        userService.userContextRole(userVo);
        return new ResponseResult(true, 200, "success", null);
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {
        //获取请求头中的token信息
        String header_token = request.getHeader("Authorization");

        //获取session中的access_token信息
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");

        //判断token是否一致
        if (header_token.equals(access_token)) {
            Integer userId = (Integer) session.getAttribute("user_id");
            ResponseResult responseResult = userService.getUserPermissions(userId);
            return responseResult;
        }
        return null;
    }
}
