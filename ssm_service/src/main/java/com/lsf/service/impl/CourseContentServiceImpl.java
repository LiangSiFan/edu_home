package com.lsf.service.impl;

import com.lsf.dao.CourseContentMapper;
import com.lsf.domain.Course;
import com.lsf.domain.CourseLesson;
import com.lsf.domain.CourseSection;
import com.lsf.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author LSF
 * 2022-03-06 18:56
 */
@Service
public class CourseContentServiceImpl implements CourseContentService {
    @Autowired
    private CourseContentMapper courseContentMapper;

    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        return courseContentMapper.findSectionAndLessonByCourseId(courseId);
    }

    @Override
    public Course findCourseByCourseId(Integer courseId) {

        return courseContentMapper.findCourseByCourseId(courseId);
    }

    @Override
    public void savaCourseSection(CourseSection courseSection) {
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.savaCourseSection(courseSection);
    }

    @Override
    public void updateCourseSection(CourseSection courseSection) {
        courseSection.setUpdateTime(new Date());
        courseContentMapper.updateCourseSection(courseSection);
    }

    @Override
    public void updateSectionStatus(Integer id, int status) {
        CourseSection courseSection = new CourseSection();
        courseSection.setUpdateTime(new Date());
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseContentMapper.updateSectionStatus(courseSection);
    }

    @Override
    public void savaCourseLesson(CourseLesson courseLesson) {
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);

        courseContentMapper.savaCourseLesson(courseLesson);
    }
}
