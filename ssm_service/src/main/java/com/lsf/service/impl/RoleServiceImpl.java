package com.lsf.service.impl;

import com.lsf.dao.RoleMapper;
import com.lsf.domain.Role;
import com.lsf.domain.RoleMenuVo;
import com.lsf.domain.Role_menu_relation;
import com.lsf.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author LSF
 * 2022-03-09 19:50
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        return roleMapper.findMenuByRoleId(roleId);
    }

    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        //清除中间表关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());
        //循环插入新的中间关系
        for(Integer mid:roleMenuVo.getMenuIdList()){
            Role_menu_relation rmr = new Role_menu_relation();
            rmr.setMenuId(mid);
            rmr.setRoleId(roleMenuVo.getRoleId());

            Date date = new Date();
            rmr.setCreatedTime(date);
            rmr.setUpdatedTime(date);

            rmr.setCreatedBy("system");
            rmr.setUpdatedby("system");

            roleMapper.roleContextMenu(rmr);
        }
    }

    @Override
    public void deleteRole(Integer id) {
        //删除中间表信息
        roleMapper.deleteRoleContextMenu(id);
        //删除角色信息
        roleMapper.deleteRole(id);
    }
}
