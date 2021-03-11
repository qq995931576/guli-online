package com.achang.voe.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/******
 @author 阿昌
 @create 2021-03-02 19:41
 *******
 */
public interface VodService {

    //阿里云上传视频
    String uploadVideoAliyun(MultipartFile file);

    //根据小节id删除一个视频
    void removeAliyunVideoById(String id);

    //根据id删除多个阿里云视频
    void removeMoreVideo(List<String> videoIdList);

    //根据视频id获取视频凭证
    String getPlayAuth(String id);
}
