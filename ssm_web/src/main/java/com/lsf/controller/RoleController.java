package com.lsf.controller;

import com.lsf.domain.Menu;
import com.lsf.domain.ResponseResult;
import com.lsf.domain.Role;
import com.lsf.domain.RoleMenuVo;
import com.lsf.service.MenuService;
import com.lsf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LSF
 * 2022-03-09 19:52
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {
        List<Role> roleList = roleService.findAllRole(role);
        return new ResponseResult(true, 200, "success", roleList);
    }
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",menuList);
        return new ResponseResult(true, 200, "success", map);
    }
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(@RequestParam Integer roleId){
        List<Integer> roleList = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true, 200, "success", roleList);
    }

    @RequestMapping("/roleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.roleContextMenu(roleMenuVo);
        return new ResponseResult(true, 200, "success", null);
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(@RequestParam Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true, 200, "success", null);
    }
}
