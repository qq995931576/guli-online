package com.achang.eduservice.service.impl;

import com.achang.eduservice.entity.EduCourse;
import com.achang.eduservice.entity.EduCourseDescription;
import com.achang.eduservice.entity.EduTeacher;
import com.achang.eduservice.entity.EduVideo;
import com.achang.eduservice.entity.frontVo.CourseFrontVo;
import com.achang.eduservice.entity.frontVo.CourseWebVo;
import com.achang.eduservice.entity.vo.CourseInfoForm;
import com.achang.eduservice.entity.vo.CoursePublishVo;
import com.achang.eduservice.entity.vo.CourseQuery;
import com.achang.eduservice.entity.vo.TeacherQuery;
import com.achang.eduservice.mapper.EduCourseMapper;
import com.achang.eduservice.service.EduChapterService;
import com.achang.eduservice.service.EduCourseDescriptionService;
import com.achang.eduservice.service.EduCourseService;
import com.achang.eduservice.service.EduVideoService;
import com.achang.servicebase.exceptionHandler.AchangException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author achang
 * @since 2021-02-28
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    //课程描述注入
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    //课程小节注入
    @Autowired
    private EduVideoService eduVideoService;

    //课程章节注入
    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduCourseMapper eduCourseMapper;

    @Override
    public String saveCourseInfo(CourseInfoForm courseInfoForm) {
        //向课程表里面添加课程基本信息
        //CourseInfoForm对象 转换成 EduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        int result = baseMapper.insert(eduCourse);

        if (result <= 0) {//表示添加失败
            throw new AchangException(20001, "添加课程信息失败");
        }

        //获取添加之后课程信息的id
        String cid = eduCourse.getId();

        //想课程简介表里面添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());

        //手动设置描述课程表的id，与上面的课程信息表id关联
        eduCourseDescription.setId(cid);

        eduCourseDescriptionService.save(eduCourseDescription);

        return cid;
    }

    @Override
    public CourseInfoForm getCourseInfo(String courseId) {
        //查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse, courseInfoForm);

        //查询简介表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoForm.setDescription(courseDescription.getDescription());

        return courseInfoForm;
    }

    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        //1、修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update <= 0) {
            throw new AchangException(20001, "修改课程信息失败");
        }

        //2、修改描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescription.setId(courseInfoForm.getId());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    //根据课程id查询课程确认信息
    @Override
    public CoursePublishVo getPublishCourseInfo(String courseId) {
        return eduCourseMapper.getPublishCourseInfo(courseId);
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {

        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();

        //取出值，判断他们是否有值
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();

        //判断条件值是否为空，如果不为空，拼接条件
        //判断是否有传入教师名
        if (!StringUtils.isEmpty(title)) {
            //构建条件
            wrapper.like("title", title);//参数1：数据库字段名； 参数2：模糊查询的值
        }
        //判断是否传入状态
        if (!StringUtils.isEmpty(status)) {
            //构造条件
            wrapper.eq("status", status);
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //带上门判断后的条件进行分页查询
        baseMapper.selectPage(pageParam, wrapper);
    }

    //删除课程
    @Override
    public boolean removeCourse(String id) {
//        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
//        wrapper.eq("course_id",id);
//        EduVideo eduVideo = eduVideoService.getOne(wrapper);
//        String VideoId = eduVideo.getId();

        //1、根据课程id删除小节
        eduVideoService.removeVideoByCourseId(id);

        //2、根据课程id删除章节部分
        eduChapterService.removeChapterByCourseId(id);

        //3、根据课程id删除课程描述
        eduCourseDescriptionService.removeById(id);

        //4、根据课程id删除课程本身
        int result = baseMapper.deleteById(id);

        if (result>=1){
            return true;
        }else {
            throw new AchangException(20001,"删除失败");
        }


    }

    //查询8个热门课程
    @Override
    @Cacheable(key = "'selectHotCourse'",value = "course")
    public List<EduCourse> selectHotCourse() {

        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("view_count");
        wrapper.last("limit 8");

        return baseMapper.selectList(wrapper);

    }

    //前台多条件分页查询
    @Override
    public Map<String, Object> getCourseFrontInfo(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {

        String title = null;
        String subjectId = null;
        String subjectParentId = null;
        String gmtCreateSort = null;
        String buyCountSort = null;
        String priceSort = null;
        String teacherId = null;

        if (!StringUtils.isEmpty(courseFrontVo)){
            title = courseFrontVo.getTitle();
            subjectId = courseFrontVo.getSubjectId();
            subjectParentId = courseFrontVo.getSubjectParentId();
            gmtCreateSort = courseFrontVo.getGmtCreateSort();
            buyCountSort = courseFrontVo.getBuyCountSort();
            priceSort = courseFrontVo.getPriceSort();
            teacherId = courseFrontVo.getTeacherId();
        }


        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，不为空拼接条件
        if (!StringUtils.isEmpty(subjectParentId)){//一级分类
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)){//二级分类
            wrapper.eq("subject_id",subjectId);
        }
        if (!StringUtils.isEmpty(buyCountSort)){//关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(priceSort)){//价格
            wrapper.orderByDesc("price");
        }
        if (!StringUtils.isEmpty(gmtCreateSort)){//最新，创建时间
            wrapper.orderByDesc("gmt_create");
        }


        baseMapper.selectPage(pageCourse, wrapper);

        long total = pageCourse.getTotal();//总记录数
        List<EduCourse> courseList = pageCourse.getRecords();//数据集合
        long size = pageCourse.getSize();//每页记录数
        long current = pageCourse.getCurrent();//当前页
        long pages = pageCourse.getPages();//总页数
        boolean hasPrevious = pageCourse.hasPrevious();//是否有上一页
        boolean hasNext = pageCourse.hasNext();//是否有下一页

        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("list",courseList);
        map.put("size",size);
        map.put("current",current);
        map.put("pages",pages);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);


        return map;
    }

    //前台根据课程id，查询课程基础信息
    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }

}
