package com.lsf.dao;

import com.lsf.domain.PromotionAd;

import java.util.List;

/**
 * @author LSF
 * 2022-03-07 20:00
 */
public interface PromotionAdMapper {
    /**
     * 分页查询广告信息
     *
     * @return PromotionAd
     */
    List<PromotionAd> findAllPromotionAdByPage();

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
  * @param promotionAd promotionAd
  */
 void updatePromotionAdStatus(PromotionAd promotionAd);
}
