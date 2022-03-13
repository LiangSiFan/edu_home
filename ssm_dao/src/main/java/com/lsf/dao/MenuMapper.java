package com.lsf.dao;

import com.lsf.domain.Menu;

import java.util.List;

/**
 * @author LSF
 * 2022-03-09 20:31
 */
public interface MenuMapper {
    /**
     * 菜单分级查询
     *
     * @param id -1传入父级菜单信息查询父际菜单对应的子菜单
     * @return menu
     */
    List<Menu> findSubMenuListByPid(Integer id);

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
