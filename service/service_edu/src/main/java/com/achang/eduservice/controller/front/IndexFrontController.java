package com.achang.eduservice.controller.front;

import com.achang.commonutils.R;
import com.achang.eduservice.entity.EduCourse;
import com.achang.eduservice.entity.EduTeacher;
import com.achang.eduservice.service.EduCourseService;
import com.achang.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/******
 @author 阿昌
 @create 2021-03-04 17:20
 *******
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/indexFront")
public class IndexFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询前8条热门课程，查询前4个名师
    @GetMapping("/index")
    public R index(){

        //调用查询前8热门课程的方法
        List<EduCourse> courseList = eduCourseService.selectHotCourse();
        //查询前4张热门讲师
        List<EduTeacher> teacherList = eduTeacherService.selectHotTeacher();

        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }


}
