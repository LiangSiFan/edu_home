package com.lsf.dao;

import com.lsf.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LSF
 * 2022-03-07 21:52
 */
public interface UserMapper {
    /**
     * 根据条件查询所有用户
     *
     * @param userVo userVo
     * @return user
     */
    List<User> findAllUserByPage(UserVo userVo);

    /**
     * 更新账户状态
     *
     * @param id     id
     * @param status status
     */
    void updateUserStatus(@Param("id") Integer id, @Param("status") String status);

    /**
     * 登录
     *
     * @param user user
     * @return user
     */
    User login(User user);

    /**
     * 根据id查询当前用户角色
     *
     * @param id id
     * @return role
     */
    List<Role> findUserRelationRoleById(int id);

    /**
     * 清楚用户id中间表
     *
     * @param userId id
     */
    void deleteUserContextRole(Integer userId);

    /**
     * 保存用户角色信息
     *
     * @param user_role_relation user_role_relation
     */
    void userContextRole(User_Role_relation user_role_relation);


    /**
     * 根据角色id,查询角色拥有的顶级菜单信息
     *
     * @param ids ids
     * @return menu
     */
    List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 根据PID 查询子菜单信息
     * @param pid pid
     * @return menu
     */
    List<Menu> findSubMenuByPid(int pid);

    /**
     * 获取用户拥有的资源权限信息
     * @param ids
     * @return menu
     */
    List<Resource> findResourceByRoleId(List<Integer> ids);
}
