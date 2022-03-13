package com.lsf.service;

import com.github.pagehelper.PageInfo;
import com.lsf.domain.PromotionAd;
import com.lsf.domain.PromotionAdVo;

/**
 * @author LSF
 * 2022-03-07 20:30
 */
public interface PromotionAdService {
    /**
     * 分页查询广告信息
     *
     * @param promotionAdVo vo
     * @return PromotionAd
     */
    PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    /**
     * 新建广告信息
     *
     * @param promotionAd promotionAd
     */
    void savePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告信息
     *
     * @param promotionAd
     */
    void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告信息
     *
     * @param id id
     * @return PromotionAd
     */
    PromotionAd findPromotionAdById(Integer id);

    /**
     * 更改广告状态
     *
     * @param id id
     * @param status status
     */
    void updatePromotionAdStatus(Integer id, Integer status);
}
