package com.lsf.controller;

import com.lsf.domain.PromotionSpace;
import com.lsf.domain.ResponseResult;
import com.lsf.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LSF
 * 2022-03-07 18:47
 */
@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService spaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace() {
        List<PromotionSpace> spaceList = spaceService.findAllPromotionSpace();
        ResponseResult responseResult = new ResponseResult(true, 200, "success", spaceList);
        return responseResult;
    }

    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace) {
        if (promotionSpace.getId() == null) {
            spaceService.savePromotionSpace(promotionSpace);
            ResponseResult responseResult = new ResponseResult(true, 200, "saveSuccess", null);
            return responseResult;
        } else {
            spaceService.updatePromotionSpace(promotionSpace);
            ResponseResult responseResult = new ResponseResult(true, 200, "updateSuccess", null);
            return responseResult;
        }
    }

    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(@RequestParam Integer id) {
        PromotionSpace promotionSpace = spaceService.findPromotionSpaceById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "Success", promotionSpace);
        return responseResult;
    }
}
