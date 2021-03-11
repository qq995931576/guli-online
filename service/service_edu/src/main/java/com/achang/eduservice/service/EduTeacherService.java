package com.achang.eduservice.service;

import com.achang.eduservice.entity.EduTeacher;
import com.achang.eduservice.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author achang
 * @since 2021-02-23
 */
public interface EduTeacherService extends IService<EduTeacher> {
    //多条件查询讲师带分页
    void pageQuery(Page<EduTeacher> pageParam,TeacherQuery teacherQuery);

    //查询前4个热门讲师
    List<EduTeacher> selectHotTeacher();

    //前台系统分页查询讲师的方法
    Map<String, Object> getTeacherFrontPageList(Page<EduTeacher> teacherPage);
}
