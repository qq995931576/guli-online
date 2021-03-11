package com.achang.eduservice.controller.front;

import com.achang.commonutils.JwtUtils;
import com.achang.commonutils.R;
import com.achang.eduservice.client.OrderClient;
import com.achang.eduservice.entity.EduCourse;
import com.achang.eduservice.entity.chapter.ChapterVo;
import com.achang.eduservice.entity.frontVo.CourseFrontVo;
import com.achang.eduservice.entity.frontVo.CourseWebVo;
import com.achang.eduservice.service.EduChapterService;
import com.achang.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/******
 @author 阿昌
 @create 2021-03-07 13:21
 *******
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/courseFront")
public class CourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;
    
    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private OrderClient orderClient;

    //前台多条件分页查询
    @PostMapping("/getConditionPage/{page}/{limit}")
    public R getConditionPage(@PathVariable Long page,
                              @PathVariable Long limit,
                              @RequestBody(required = false) CourseFrontVo courseFrontVo){
        Page<EduCourse> pageCourse = new Page<>(page, limit);
        Map<String,Object> map = eduCourseService.getCourseFrontInfo(pageCourse,courseFrontVo);

        return R.ok().data(map);
    }

    //课程详情的方法
    @GetMapping("/getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable("courseId") String courseId, HttpServletRequest request){
        boolean isBuyCourse = false;

        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);

        //根据课程id，查询章节和小节信息
        List<ChapterVo> chapterVideoList = eduChapterService.getChapterVideoByCourseId(courseId);

        //获取用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        if (!StringUtils.isEmpty(memberId)){
            //根据课程id、用户id，查询课程是否已经购买
            isBuyCourse = orderClient.isBuyCourse(memberId, courseId);
        }

        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList).data("isBuy",isBuyCourse);

    }


















}
