package com.achang.eduservice.controller.front;

import com.achang.commonutils.R;
import com.achang.eduservice.entity.EduCourse;
import com.achang.eduservice.entity.EduTeacher;
import com.achang.eduservice.service.EduCourseService;
import com.achang.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/******
 @author 阿昌
 @create 2021-03-06 19:29
 *******
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacherFront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;


    //前台系统分页查询讲师的方法
    //page：当前页 ，limit：显示记录数
    @GetMapping("/getTeacherFrontPageList/{page}/{limit}")
    public R getTeacherFrontPageList(@PathVariable Long page,@PathVariable Long limit){
        Page<EduTeacher> teacherPage = new Page<>(page, limit);

        Map<String,Object> map = eduTeacherService.getTeacherFrontPageList(teacherPage);

        //返回分页中的所有数据
        return R.ok().data(map);

    }

    //根据id查询讲师信息（讲师本身信息+讲师所讲课程信息）
    @GetMapping("/getTeacherInfo/{id}")
    public R getTeacherInfo(@PathVariable String id){
        //查询讲师信息
        EduTeacher teacher = eduTeacherService.getById(id);

        //查询讲师所讲课程信息
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",id);
        List<EduCourse> courseList = eduCourseService.list(wrapper);

        return R.ok().data("teacher",teacher).data("courseList",courseList);
    }






}
