package com.lsf.service;

import com.lsf.domain.Course;
import com.lsf.domain.CourseLesson;
import com.lsf.domain.CourseSection;

import java.util.List;

/**
 * @author LSF
 * 2022-03-06 18:55
 */
public interface CourseContentService {

    /**
     * 查询课程下的章节和课时信息
     *
     * @param courseId
     * @return
     */
    List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);

    /**
     * 根据id查询课时信息
     *
     * @param courseId id
     * @return course
     */
    Course findCourseByCourseId(Integer courseId);

    /**
     * 新建章节信息
     *
     * @param courseSection courseSection
     */
    void savaCourseSection(CourseSection courseSection);

    /**
     * 修改章节信息
     *
     * @param courseSection courseSection
     */
    void updateCourseSection(CourseSection courseSection);

    /**
     * 更改章节状态
     * @param id id
     * @param status status
     */
    void updateSectionStatus(Integer id, int status);

    /**
     * 新建课程
     *
     * @param courseLesson courseLesson
     */
    void savaCourseLesson(CourseLesson courseLesson);
}
