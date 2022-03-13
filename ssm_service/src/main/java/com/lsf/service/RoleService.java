package com.lsf.service;

import com.github.pagehelper.PageInfo;
import com.lsf.domain.Role;
import com.lsf.domain.RoleMenuVo;

import java.util.List;

/**
 * @author LSF
 * 2022-03-09 19:48
 */
public interface RoleService {
    /**
     * 分页查询所有角色
     *
     * @param role role
     * @return role
     */
    List<Role> findAllRole(Role role);

    /**
     * 根据角色id查询关联的菜单信息
     * @param roleId roleId
     * @return menuId
     */
    List<Integer> findMenuByRoleId(Integer roleId);

    /**
     * 为角色分配菜单权限
     * @param roleMenuVo roleMenuVo
     */
    void roleContextMenu(RoleMenuVo roleMenuVo);

    /**
     * 删除角色
     * @param id id
     */
    void deleteRole(Integer id);
}
