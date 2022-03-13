package com.lsf.dao;

import com.lsf.domain.Role;
import com.lsf.domain.Role_menu_relation;

import java.util.List;

/**
 * @author LSF
 * 2022-03-09 19:29
 */
public interface RoleMapper {
    /**
     * 查询所有角色
     *
     * @param role
     * @return
     */
    List<Role> findAllRole(Role role);

    /**
     * 根据角色id查询关联的菜单信息
     * @param roleId roleId
     * @return menuId
     */
    List<Integer> findMenuByRoleId(Integer roleId);

    /**
     * 根据id删除角色菜单信息
     * @param rid id
     */
    void deleteRoleContextMenu(Integer rid);

    /**
     * 为角色分配菜单
     * @param role_menu_relation rmr
     */
    void roleContextMenu(Role_menu_relation role_menu_relation);

    /**
     * 删除角色
     * @param id id
     */
    void deleteRole(Integer id);
}
