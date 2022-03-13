package com.lsf.service.impl;

import com.lsf.dao.PromotionSpaceMapper;
import com.lsf.domain.PromotionSpace;
import com.lsf.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author LSF
 * 2022-03-07 18:44
 */
@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {
    @Autowired
    private PromotionSpaceMapper spaceMapper;

    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        return spaceMapper.findAllPromotionSpace();
    }

    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
        Date date = new Date();
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        //未删除
        promotionSpace.setIsDel(0);
        //保存
        spaceMapper.savePromotionSpace(promotionSpace);
    }

    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        Date date = new Date();
        promotionSpace.setUpdateTime(date);
        spaceMapper.updatePromotionSpace(promotionSpace);
    }

    @Override
    public PromotionSpace findPromotionSpaceById(Integer id) {
        return spaceMapper.findPromotionSpaceById(id);
    }
}
