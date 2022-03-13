package com.lsf.dao;

import com.lsf.domain.Course;
import com.lsf.domain.CourseVo;

import java.util.List;

/**
 * @author LSF
 * 2022-03-06 10:37
 */
public interface CourseMapper {
    /**
     * 多条件查询课程信息
     *
     * @param courseVO courseName,status
     * @return Course
     */
    List<Course> findCourseByCondition(CourseVo courseVO);

    /**
     * 新增课程信息
     *
     * @param course 课程
     */
    void saveCourse(Course course);

    /**
     * 根据id查询课程信息及关联的讲师信息
     *
     * @param id id
     * @return course
     */
    CourseVo findCourseById(Integer id);

    /**
     * 修改课程信息
     * @param course course
     */
    void updateCourse(Course course);

    /**
     * 更改课程状态
     * @param course course
     */
    void updateCourseStatus(Course course);

}
