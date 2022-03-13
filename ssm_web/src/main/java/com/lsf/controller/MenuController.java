package com.lsf.controller;

import com.lsf.domain.Menu;
import com.lsf.domain.ResponseResult;
import com.lsf.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author LSF
 * 2022-03-09 20:45
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> menuList = menuService.findAllMenu();
        ResponseResult responseResult = new ResponseResult(true, 200, "success", menuList);
        return responseResult;
    }

    /**
     * 根据id查询菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam int id) {
        //查询所有父菜单信息
        if (id == -1) {
            //添加操作 回显不需要查询菜单信息
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo", null);
            map.put("parentMenuList", menuList);
            ResponseResult responseResult = new ResponseResult(true, 200, "success", map);
            return responseResult;
        } else {
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            HashMap<String, Object> map = new HashMap<>();
            map.put("menuInfo", menu);
            map.put("parentMenuList", menuList);
            ResponseResult responseResult = new ResponseResult(true, 200, "success", map);
            return responseResult;
        }
    }

}
