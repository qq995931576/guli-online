package com.achang.staservice.client;

import com.achang.commonutils.R;
import com.achang.staservice.client.impl.UcenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/******
 @author 阿昌
 @create 2021-03-09 14:43
 *******
 */
@Component
@FeignClient(value = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {
    //根据日期，获取那天注册人数
    @GetMapping("/serviceUcenter/ucenter-member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
