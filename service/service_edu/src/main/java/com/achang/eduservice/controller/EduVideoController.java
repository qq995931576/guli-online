package com.achang.eduservice.controller;


import com.achang.commonutils.R;
import com.achang.eduservice.entity.EduVideo;
import com.achang.eduservice.service.EduVideoService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-02-28
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin //解决跨域问题
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    //添加小节
    @PostMapping("/addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }


    //删除课程
    @DeleteMapping("/deleteVideo/{id}")
    public R deleteVideo(@PathVariable String id){
//        eduVideoService.removeById(id);
        eduVideoService.removeVideoByCourseId(id);
        return R.ok();
    }

    //删除小节
    @DeleteMapping("/deleteVideoByid/{id}")
    public R deleteVideoByid(@PathVariable String id){
        eduVideoService.removeVideoIdAndAliyun(id);
        return R.ok();
    }



    //修改小节
    @PostMapping("/updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }

    //根据小节id查询
    @GetMapping("/getVideoById/{videoId}")
    public R getVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);
        return R.ok().data("video",eduVideo);
    }

}

