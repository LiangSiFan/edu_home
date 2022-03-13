package com.lsf.dao;

import com.lsf.domain.Teacher;

/**
 * @author LSF
 * 2022-03-06 13:41
 */
public interface TeacherMapper {

    /**
     * 新增讲师信息
     *
     * @param teacher 教师信息
     */
    void savaTeacher(Teacher teacher);

    /**
     * 修改讲师信息
     * @param teacher
     */
    void updateTeacher(Teacher teacher);
}
