package com.lsf.service.impl;

import com.lsf.dao.MenuMapper;
import com.lsf.domain.Menu;
import com.lsf.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LSF
 * 2022-03-09 20:42
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(Integer pid) {
        return menuMapper.findSubMenuListByPid(pid);

    }

    @Override
    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }

    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }
}
