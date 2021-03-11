package com.achang.eduservice.client;

import com.achang.commonutils.R;
import com.achang.commonutils.vo.EduCourseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/******
 @author 阿昌
 @create 2021-03-08 15:59
 *******
 */
@Component
@FeignClient(name = "service-edu")
public interface ServiceEduClient {

    //根据课程id，获取课程信息
    @PostMapping("/eduservice/edu-course/getCourseInfoByIdOrder/{courseId}")
    public EduCourseVo getCourseInfoByIdOrder(@PathVariable("courseId") String courseId);

}
