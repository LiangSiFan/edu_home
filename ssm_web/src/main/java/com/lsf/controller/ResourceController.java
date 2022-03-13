package com.lsf.controller;

import com.github.pagehelper.PageInfo;
import com.lsf.domain.Resource;
import com.lsf.domain.ResourceCategory;
import com.lsf.domain.ResourceVo;
import com.lsf.domain.ResponseResult;
import com.lsf.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LSF
 * 2022-03-12 22:40
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo) {
     PageInfo<Resource> pageInfo = resourceService.findAllResource(resourceVo);
     ResponseResult responseResult = new ResponseResult(true, 200, "success", pageInfo);
     return responseResult;
    }
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> categoryList = resourceService.findAllResourceCategory();
        ResponseResult responseResult = new ResponseResult(true, 200, "success", categoryList);
        return responseResult;
    }
}
