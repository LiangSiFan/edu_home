package com.lsf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsf.dao.ResourceMapper;
import com.lsf.domain.Resource;
import com.lsf.domain.ResourceCategory;
import com.lsf.domain.ResourceVo;
import com.lsf.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LSF
 * 2022-03-12 22:36
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo) {
        PageHelper.startPage(resourceVo.getCurrentPage(), resourceVo.getPageSize());
        List<Resource> resourceList = resourceMapper.findAllResource(resourceVo);
        PageInfo<Resource> pageInfo = new PageInfo<>(resourceList);
        return pageInfo;
    }

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceMapper.findAllResourceCategory();
    }
}
