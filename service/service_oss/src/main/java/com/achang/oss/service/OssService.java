package com.achang.oss.service;

import org.springframework.web.multipart.MultipartFile;

/******
 @author 阿昌
 @create 2021-02-27 12:32
 *******
 */
public interface OssService {

    //上传头像到OSS
    String uploadFileAvatar(MultipartFile file);

}
