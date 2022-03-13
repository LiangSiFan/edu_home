package com.lsf.controller;

import com.lsf.domain.Course;
import com.lsf.domain.CourseVo;
import com.lsf.domain.ResponseResult;
import com.lsf.service.CourseService;
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
import java.util.List;
import java.util.Map;

/**
 * @author LSF
 * 2022-03-06 10:49
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 多条件查询课程信息
     *
     * @param courseVO courseVO
     * @return res
     */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVo courseVO) {
        List<Course> courseList = courseService.findCourseByCondition(courseVO);
        ResponseResult responseResult = new ResponseResult(true, 200, "success", courseList);
        return responseResult;
    }

    /**
     * 新增课程信息 文件上传
     *
     * @param file file
     * @return res
     */
    @RequestMapping("/fileUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
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

    /**
     * 新增或修改课程信息
     *
     * @return res
     */
    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVo courseVO) {
        try {
            if (courseVO.getId() == null) {
                //新增课程信息
                courseService.saveCourseOrTeacher(courseVO);
                ResponseResult responseResult = new ResponseResult(true, 200, "savaSuccess", null);
                return responseResult;
            } else {
                courseService.updateCourseOrTeacher(courseVO);
                ResponseResult responseResult = new ResponseResult(true, 200, "updateSuccess", null);
                return responseResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResponseResult responseResult = new ResponseResult(true, 999, "error", null);
            return responseResult;
        }
    }

    /**
     * 根据id查询课程及教师信息
     *
     * @param id id
     * @return courseVO
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(@RequestParam Integer id) {
        CourseVo courseVO = courseService.findCourseById(id);
        ResponseResult responseResult = new ResponseResult(true, 200, "success", courseVO);
        return responseResult;
    }

    /**
     * 修改课程状态
     *
     * @param id     id
     * @param status status
     * @return res
     */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(@RequestParam Integer id, @RequestParam int status) {
        //调用service完成课程状态变更
        courseService.updateCourseStatus(id, status);
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        ResponseResult responseResult = new ResponseResult(true, 200, "success", map);
        return responseResult;
    }
}
