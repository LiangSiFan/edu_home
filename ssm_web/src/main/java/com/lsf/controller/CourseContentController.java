package com.lsf.controller;

import com.lsf.domain.Course;
import com.lsf.domain.CourseLesson;
import com.lsf.domain.CourseSection;
import com.lsf.domain.ResponseResult;
import com.lsf.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LSF
 * 2022-03-06 19:00
 */
@RestController
@RequestMapping("/courseContent")
public class CourseContentController {
    @Autowired
    private CourseContentService courseContentService;

    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLesson(@RequestParam Integer courseId) {
        List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "success", sectionList);
        return responseResult;
    }

    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam Integer courseId) {
        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult responseResult = new ResponseResult(true, 200, "success", course);
        return responseResult;
    }
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection courseSection){
        if(courseSection.getCourseId()== null) {
            courseContentService.savaCourseSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "saveSuccess", null);
            return responseResult;
        }else{
            courseContentService.updateCourseSection(courseSection);
            ResponseResult responseResult = new ResponseResult(true, 200, "updateSuccess", null);
            return responseResult;
        }
    }
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam Integer id,@RequestParam int status){
        courseContentService.updateSectionStatus(id,status);
        //封装最新的状态信息
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult responseResult = new ResponseResult(true, 200, "updateSuccess", map);
        return responseResult;
    }
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody  CourseLesson courseLesson){
        if(courseLesson.getId()==null){
            courseContentService.savaCourseLesson(courseLesson);
            ResponseResult responseResult = new ResponseResult(true, 200, "saveSuccess", null);
            return responseResult;
        }else{
            return  null;
        }

    }
}
