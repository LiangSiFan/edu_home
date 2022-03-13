package com.lsf.service;

import com.lsf.domain.Course;
import com.lsf.domain.CourseVo;

import java.util.List;

/**
 * @author LSF
 * 2022-03-06 10:46
 */
public interface CourseService {

    /**
     * 多条件查询课程信息
     *
     * @param courseVO courseName,status
     * @return Course
     */
    List<Course> findCourseByCondition(CourseVo courseVO);

    /**
     * 新建课程信息
     *
     * @param courseVO 课程及讲师信息
     */
    void saveCourseOrTeacher(CourseVo courseVO);

    /**
     * 根据id查询课程信息及关联的讲师信息
     *
     * @param id id
     * @return course
     */
    CourseVo findCourseById(Integer id);

    /**
     * 更新课程及讲师信息
     * @param courseVO courseVO
     */
    void updateCourseOrTeacher(CourseVo courseVO);

    /**
     * 更改课程状态
     * @param id  id
     * @param status status
     */
    void updateCourseStatus(Integer id ,int status);


}
