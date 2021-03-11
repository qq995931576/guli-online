package com.achang.eduservice.mapper;

import com.achang.eduservice.entity.EduCourse;
import com.achang.eduservice.entity.frontVo.CourseWebVo;
import com.achang.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author achang
 * @since 2021-02-28
 */
@Component
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String courseId);

    //前台根据课程id，查询课程基础信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
