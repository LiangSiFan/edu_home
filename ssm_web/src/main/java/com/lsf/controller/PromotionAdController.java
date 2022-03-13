package com.lsf.controller;

import com.github.pagehelper.PageInfo;
import com.lsf.domain.PromotionAd;
import com.lsf.domain.PromotionAdVo;
import com.lsf.domain.ResponseResult;
import com.lsf.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LSF
 * 2022-03-07 20:40
 */
@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {
    @Autowired
    private PromotionAdService adService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo) {
        PageInfo<PromotionAd> promotionAdByPage = adService.findAllPromotionAdByPage(promotionAdVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "success", promotionAdByPage);
        return responseResult;
    }

    @RequestMapping("/promotionAdUpload")
    public ResponseResult promotionAdUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            //1.判断接收到的文件是否为空
            if (file.isEmpty()) {
                throw new RuntimeException();
            }
            //2.获取项目部署路径
            //获取到tomcat/webapps目录下的项目地址
            String realPath = request.getServletContext().getRealPath("/");
            //D:\Java\apache-tomcat-8.5.72\webapps
            String webPath = realPath.substring(0, realPath.indexOf("ssm_web"));
            //3.获取原文件名
            String originalFilename = file.getOriginalFilename();

            //4.生成新文件名
            String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

            //5.文件上传
            //D:\Java\apache-tomcat-8.5.72\webapps
            String uploadPath = webPath + "upload\\";
            File filePath = new File(uploadPath, newFileName);
            //如果目录不存在就创建目录
            if (filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录" + filePath);
            }


            //图片上传
            file.transferTo(filePath);

            //6.将文件和文件路径放回
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("fileName", newFileName);
            hashMap.put("filePath", "http://localhost:8080/upload/" + newFileName);

            ResponseResult responseResult = new ResponseResult(true, 200, "success", hashMap);

            return responseResult;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
        if (promotionAd.getId() == null) {
            adService.savePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "saveSuccess", null);
            return result;
        } else {
            adService.updatePromotionAd(promotionAd);
            ResponseResult result = new ResponseResult(true, 200, "updateSuccess", null);
            return result;
        }
    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam Integer id) {
        PromotionAd promotionAd = adService.findPromotionAdById(id);
        ResponseResult result = new ResponseResult(true, 200, "success", promotionAd);
        return result;
    }

    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam Integer id, @RequestParam Integer status) {
        //执行修改操作
        if (status == 1) {
            adService.updatePromotionAdStatus(id, status);
        } else {
            adService.updatePromotionAdStatus(id, 0);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("status", status);
        ResponseResult result = new ResponseResult(true, 200, "success", map);
        return result;
    }
}
