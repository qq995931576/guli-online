package com.achang.eduservice.service;

import com.achang.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author achang
 * @since 2021-02-28
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String id);

    void removeVideoIdAndAliyun(String id);

}
