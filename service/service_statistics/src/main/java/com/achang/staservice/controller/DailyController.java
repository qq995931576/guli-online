package com.achang.staservice.controller;


import com.achang.commonutils.R;
import com.achang.staservice.client.UcenterClient;
import com.achang.staservice.service.DailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-03-09
 */
@RestController
@RequestMapping("/staservice/daily")
@CrossOrigin //解决跨域问题
public class DailyController {

    @Autowired
    private DailyService dailyService;

    //统计某一天注册人数
    @PostMapping("/createStatisticsByDay/{day}")
    public R createStatisticsByDay(@PathVariable String day){
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("/showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,@PathVariable String begin,@PathVariable String end){

        Map<String,Object> map = dailyService.getShowData(type,begin,end);

        return R.ok().data(map);
    }

}

