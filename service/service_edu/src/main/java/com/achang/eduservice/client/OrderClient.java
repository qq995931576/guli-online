package com.achang.eduservice.client;

import com.achang.eduservice.client.impl.OrderClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/******
 @author 阿昌
 @create 2021-03-09 11:48
 *******
 */
@Component
@FeignClient(value = "service-order",fallback = OrderClientImpl.class)
public interface OrderClient {

    //根据【用户id、课程id】查询订单表中的状态
    @GetMapping("/eduorder/t-order/isBuyCourse/{memberId}/{courseId}")
    public Boolean isBuyCourse(@PathVariable("memberId") String memberId, @PathVariable("courseId") String courseId);


}
