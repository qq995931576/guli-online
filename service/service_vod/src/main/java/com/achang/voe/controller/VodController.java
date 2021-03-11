package com.achang.voe.controller;

import com.achang.commonutils.R;
import com.achang.servicebase.exceptionHandler.AchangException;
import com.achang.voe.service.VodService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/******
 @author 阿昌
 @create 2021-03-02 19:38
 *******
 */
@RestController
@CrossOrigin
@RequestMapping("/eduvod/video")
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("/uploadAliyunVideo")
    public R  uploadAliyunVideo(MultipartFile file){
        //返回上传视频的id
        String videoId = vodService.uploadVideoAliyun(file);
        return R.ok().data("videoId",videoId);
    }


    //根据视频id删除1个阿里云视频
    @DeleteMapping("/removeAliyunVideoById/{id}")
    public R removeAliyunVideoById(@PathVariable String id){
        vodService.removeAliyunVideoById(id);
        return R.ok();
    }

    //根据id删除多个阿里云视频
    @DeleteMapping("/removeBatch")
    public R removeBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMoreVideo(videoIdList);
        return R.ok();
    }

    //根据视频id获取视频凭证
    @GetMapping("/getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){
        try {
            String playAuth = vodService.getPlayAuth(id);
            return R.ok().data("playAuth",playAuth);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AchangException(20001,"获取视频凭证失败");
        }

    }









}
