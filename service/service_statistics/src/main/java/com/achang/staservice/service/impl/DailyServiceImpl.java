package com.achang.staservice.service.impl;

import com.achang.commonutils.R;
import com.achang.staservice.client.UcenterClient;
import com.achang.staservice.entity.Daily;
import com.achang.staservice.mapper.DailyMapper;
import com.achang.staservice.service.DailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author achang
 * @since 2021-03-09
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    private UcenterClient ucenterClient;

    //统计某一天注册人数
    @Override
    public void createStatisticsByDay(String day) {
        //添加之前先删除表相同的数据
        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);


        //远程调用得到某一天的注册人数
        R registerR = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) registerR.getData().get("countRegister");

        //其他的数据类似，也是通过远程调用，获取数据返回即可，下面使用随机数模拟

        //把获取到的数据
        Daily daily = new Daily();
        daily.setRegisterNum(countRegister);//注册人数
        daily.setCourseNum(RandomUtils.nextInt(100,200));
        daily.setLoginNum(RandomUtils.nextInt(200,300));//登录数
        daily.setVideoViewNum(RandomUtils.nextInt(200,300));//视频流量数
        daily.setDateCalculated(day);//统计日期

        //添加到数据库中
        baseMapper.insert(daily);

    }


    //图表显示，返回两部分数据，日期json数组，数量json数组
    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        //根据条件查询对应的数据
        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.select("date_calculated",type);
        wrapper.between("date_calculated",begin,end);

        List<Daily> dailyList = baseMapper.selectList(wrapper);

        //前端要求数组json结果，对应后端为List集合
        //创建两个list集合，一个放日期X轴，一个放数量Y轴
        List<String> xlist = new ArrayList<>();
        List<Integer> ylist = new ArrayList<>();

        for (Daily daily : dailyList) {
            xlist.add(daily.getDateCalculated());

            //判断查询的哪个字段
            if ("register_num".equals(type)){
                ylist.add(daily.getRegisterNum());
            }
            if ("login_num".equals(type)){
                ylist.add(daily.getLoginNum());
            }
            if ("video_view_num".equals(type)){
                ylist.add(daily.getVideoViewNum());
            }
            if ("course_num".equals(type)){
                ylist.add(daily.getCourseNum());
            }

        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("xlist",xlist);
        map.put("ylist",ylist);

        return map;
    }

}
