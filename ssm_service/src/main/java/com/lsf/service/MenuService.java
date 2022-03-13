package com.lsf.service;

import com.lsf.domain.Menu;

import java.util.List;

/**
 * @author LSF
 * 2022-03-09 20:42
 */
public interface MenuService {
    /**
     * 菜单分级查询
     *
     * @param pid -1传入父级菜单信息查询父际菜单对应的子菜单
     * @return menu
     */
    List<Menu> findSubMenuListByPid(Integer pid);

    /**
     * 查询所有菜单
     *
     * @return menu
     */
    List<Menu> findAllMenu();

    /**
     * 根据id查询菜单信息
     *
     * @param id id
     * @return menu
     */
    Menu findMenuById(Integer id);
}
