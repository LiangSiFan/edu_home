package com.lsf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsf.dao.UserMapper;
import com.lsf.domain.*;
import com.lsf.service.UserService;
import com.lsf.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author LSF
 * 2022-03-07 22:12
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {
        PageHelper.startPage(userVo.getCurrentPage(), userVo.getPageSize());
        List<User> userList = userMapper.findAllUserByPage(userVo);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    public void updateUserStatus(Integer id, String status) {
        userMapper.updateUserStatus(id, status);
    }

    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if (user1 != null && Md5.verify(user.getPassword(), "lagou", user1.getPassword())) {
            return user1;
        } else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(int id) {
        return userMapper.findUserRelationRoleById(id);
    }

    @Override
    public void userContextRole(UserVo userVo) {
        //根据user_id清除中间表信息
        userMapper.deleteUserContextRole(userVo.getUserId());

        for(Integer roleId:userVo.getRoleIdList()){
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVo.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer id) {
        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        //获取角色id ，重新封装到list中
        ArrayList<Integer> roleIds = new ArrayList<>();
        for (Role role : roleList) {
            roleIds.add(role.getId());
        }
        //根据角色id查询父菜单
        List<Menu> parentMenuList = userMapper.findParentMenuByRoleId(roleIds);

        //查询封装父菜单关联的子菜单
        for (Menu menu : parentMenuList) {
            List<Menu> subMenuList = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenuList);
        }
        //获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);
        //封装数据返回
        HashMap<String, Object> map = new HashMap<>();
        //菜单信息
        map.put("menuList",parentMenuList);
        //权限信息
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"success",map);
    }
}
