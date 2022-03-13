package com.lsf.dao;

import com.lsf.domain.Resource;
import com.lsf.domain.ResourceCategory;
import com.lsf.domain.ResourceVo;

import java.util.List;

/**
 * @author LSF
 * 2022-03-12 22:26
 */
public interface ResourceMapper {
 /**
  * 分页、多条件查询资源
  * @param resourceVo resource
  * @return resource
  */
 List<Resource> findAllResource(ResourceVo resourceVo);

 /**
  * 查询所有资源分类
  * @return resourceCategory
  */
 List<ResourceCategory> findAllResourceCategory();
}
