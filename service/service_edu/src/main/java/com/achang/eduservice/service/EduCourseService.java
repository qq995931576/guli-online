package com.achang.eduservice.service;

import com.achang.eduservice.entity.EduCourse;
import com.achang.eduservice.entity.frontVo.CourseFrontVo;
import com.achang.eduservice.entity.frontVo.CourseWebVo;
import com.achang.eduservice.entity.vo.CourseInfoForm;
import com.achang.eduservice.entity.vo.CoursePublishVo;
import com.achang.eduservice.entity.vo.CourseQuery;
import com.achang.eduservice.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author achang
 * @since 2021-02-28
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息方法
    String saveCourseInfo(CourseInfoForm courseInfoForm);

    //根据课程id查询课程基本信息
    CourseInfoForm getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoForm courseInfoForm);

    ////根据课程id查询课程确认信息
    public CoursePublishVo getPublishCourseInfo(String courseId);

    //多条件查询讲师带分页
    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);

    //删除课程
    boolean removeCourse(String id);

    //查询前4个热门讲师
    List<EduCourse> selectHotCourse();

    //前台多条件分页查询
    Map<String, Object> getCourseFrontInfo(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    //前台根据课程id，查询课程基础信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
