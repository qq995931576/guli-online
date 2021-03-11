package com.achang.eduservice.service.impl;

import com.achang.eduservice.client.VodClient;
import com.achang.eduservice.entity.EduVideo;
import com.achang.eduservice.mapper.EduVideoMapper;
import com.achang.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author achang
 * @since 2021-02-28
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    //根据课程id删除小节
    @Override
    public void removeVideoByCourseId(String id) {

//        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
//        wrapper.eq("course_id",id);
//        EduVideo eduVideo = eduVideoService.getOne(wrapper);
//        String VideoId = eduVideo.getId();

        //根据课程id查询课程里面的所有视频
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        wrapper.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(wrapper);

        List<String> list = new ArrayList<>();
        for (EduVideo eduVideo : eduVideos) {
            String sourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(sourceId)){
                list.add(sourceId);
            }

        }

        //根据多个视频id，删除多个视频
        if (list.size()>0){
            vodClient.removeBatch(list);
        }

//        //查询云端视频id
//        EduVideo eduVideo = baseMapper.selectById(id);
//        String videoSourceId = eduVideo.getVideoSourceId();
//        //判断小节中是否有对应的视频文件
//        if (!StringUtils.isEmpty(videoSourceId)){
//            //有就删除
//            vodClient.removeAliyunVideoById(videoSourceId);
//        }

        //删除小节
//        baseMapper.deleteById(id);
        QueryWrapper<EduVideo> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("course_id", id);
        baseMapper.delete(queryWrapper2);

    }

    //根据小节id删除视频和小结内容
    @Override
    public void removeVideoIdAndAliyun(String id) {
        EduVideo eduVideo = baseMapper.selectById(id);
        String videoSourceId = eduVideo.getVideoSourceId();

        if (!StringUtils.isEmpty(videoSourceId)){
            //根据视频id删除阿里云视频
            vodClient.removeAliyunVideoById(videoSourceId);
        }

        //删除小节id
        baseMapper.deleteById(id);

    }

}
