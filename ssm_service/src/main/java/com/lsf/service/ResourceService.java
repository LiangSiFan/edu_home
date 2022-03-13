package com.lsf.service;

import com.github.pagehelper.PageInfo;
import com.lsf.domain.Resource;
import com.lsf.domain.ResourceCategory;
import com.lsf.domain.ResourceVo;

import java.util.List;

/**
 * @author LSF
 * 2022-03-12 22:34
 */
public interface ResourceService {
    /**
     * 分页查询所有资源
     *
     * @param resourceVo resourceVo
     * @return pageInfo
     */
    PageInfo<Resource> findAllResource(ResourceVo resourceVo);

    /**
     * 查询所有资源分类
     *
     * @return resourceCategory
     */
    List<ResourceCategory> findAllResourceCategory();
}
