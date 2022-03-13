package com.lsf.service.impl;

import com.lsf.dao.CourseMapper;
import com.lsf.dao.TeacherMapper;
import com.lsf.domain.Course;
import com.lsf.domain.CourseVo;
import com.lsf.domain.Teacher;
import com.lsf.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author LSF
 * 2022-03-06 10:47
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Course> findCourseByCondition(CourseVo courseVO) {
     List<Course> courseList = courseMapper.findCourseByCondition(courseVO);
     return courseList;
    }

    @Override
    public void saveCourseOrTeacher(CourseVo courseVO) {
        try {
            //封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(courseVO,course);

            //补全课程信息
            Date date=new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);
            //保存课程
            courseMapper.saveCourse(course);
            //获取新插入数据的id
            int id = course.getId();
            //封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(courseVO,teacher);
            //补全讲师信息
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);
            teacher.setCourseId(id);
            //保存讲师
            teacherMapper.savaTeacher(teacher);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public CourseVo findCourseById(Integer id) {
        CourseVo courseVO = courseMapper.findCourseById(id);
        return courseVO;
    }

    @Override
    public void updateCourseOrTeacher(CourseVo courseVO) {
        //封装课程信息
        Course course = new Course();
        BeanUtils.copyProperties(courseVO,course);
        Date date = new Date();
        course.setUpdateTime(date);
        //更新课程信息
        courseMapper.updateCourse(course);
        //封装讲师信息
        Teacher teacher = new Teacher();
        BeanUtils.copyProperties(courseVO,teacher);
        teacher.setUpdateTime(date);
        teacher.setCourseId(course.getId());
        //更新讲师信息
        teacherMapper.updateTeacher(teacher);
    }

    @Override
    public void updateCourseStatus(Integer id, int status) {
        //1.封装数据
        Course course = new Course();
        course.setId(id);
        course.setStatus(status);
        course.setUpdateTime(new Date());
        //2.调用mapper
        courseMapper.updateCourseStatus(course);
    }
}
