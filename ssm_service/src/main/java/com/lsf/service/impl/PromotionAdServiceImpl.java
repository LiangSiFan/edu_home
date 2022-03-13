package com.lsf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsf.dao.PromotionAdMapper;
import com.lsf.domain.PromotionAd;
import com.lsf.domain.PromotionAdVo;
import com.lsf.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author LSF
 * 2022-03-07 20:32
 */
@Service
public class PromotionAdServiceImpl implements PromotionAdService {
    @Autowired
    private PromotionAdMapper adMapper;

    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo) {
        PageHelper.startPage(promotionAdVo.getCurrentPage(), promotionAdVo.getPageSize());
        List<PromotionAd> promotionAdList = adMapper.findAllPromotionAdByPage();
        PageInfo<PromotionAd> pageInfo = new PageInfo<>(promotionAdList);
        return pageInfo;
    }

    @Override
    public void savePromotionAd(PromotionAd promotionAd) {
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);
        adMapper.savePromotionAd(promotionAd);
    }

    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {
        promotionAd.setUpdateTime(new Date());
        adMapper.updatePromotionAd(promotionAd);
    }

    @Override
    public PromotionAd findPromotionAdById(Integer id) {
        return adMapper.findPromotionAdById(id);
    }

    @Override
    public void updatePromotionAdStatus(Integer id, Integer status) {
        PromotionAd promotionAd = new PromotionAd();
        promotionAd.setId(id);
        promotionAd.setStatus(status);
        promotionAd.setUpdateTime(new Date());
        adMapper.updatePromotionAdStatus(promotionAd);
    }
}
