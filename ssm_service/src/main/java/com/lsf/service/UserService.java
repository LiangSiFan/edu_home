package com.lsf.service;

import com.github.pagehelper.PageInfo;
import com.lsf.domain.*;

import java.util.List;

/**
 * @author LSF
 * 2022-03-07 21:59
 */
public interface UserService {
    /**
     * 分页查询用户
     *
     * @param userVo
     * @return
     */
    PageInfo<User> findAllUserByPage(UserVo userVo);

    /**
     * 更新账户状态
     *
     * @param id     id
     * @param status status
     */
    void updateUserStatus(Integer id, String status);

    /**
     * 登录
     *
     * @param user user
     * @return user
     */
    User login(User user) throws Exception;

    /**
     * 根据id查询当前用户角色
     * @param id id
     * @return role
     */
    List<Role> findUserRelationRoleById(int id);

    /**
     * 用户关联角色
     * @param userVo userVo
     */
    void userContextRole(UserVo userVo);

    /**
     * 获取用户菜单权限信息
     * @param id id
     * @return ResponseResult
     */
    ResponseResult getUserPermissions(Integer id);
}
