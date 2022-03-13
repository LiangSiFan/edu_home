package com.lsf.service;

import com.lsf.domain.PromotionSpace;

import java.util.List;

/**
 * @author LSF
 * 2022-03-07 18:44
 */
public interface PromotionSpaceService {
    /**
     * 查询所有广告位信息
     *
     * @return PromotionSpace
     */
    List<PromotionSpace> findAllPromotionSpace();

    /**
     * 添加广告位
     *
     * @param promotionSpace space
     */
    void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 修改广告位
     *
     * @param promotionSpace space
     */
    void updatePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 根据id查询广告位
     *
     * @param id id
     * @return PromotionSpace
     */
    PromotionSpace findPromotionSpaceById(Integer id);
}
