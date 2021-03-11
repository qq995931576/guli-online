package com.achang.eduservice.controller;


import com.achang.commonutils.JwtUtils;
import com.achang.commonutils.R;
import com.achang.eduservice.entity.TOrder;
import com.achang.eduservice.service.TOrderService;
import com.achang.servicebase.exceptionHandler.AchangException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-03-08
 */
@RestController
@CrossOrigin
@RequestMapping("/eduorder/t-order")
public class TOrderController {

    @Autowired
    private TOrderService tOrderService;

    //生成订单的方法
    @PostMapping("/createOrder/{courseId}")
    public R createOrder(@PathVariable String courseId, HttpServletRequest request){
        //从请求头中获取用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //判断是否登录
        if (StringUtils.isEmpty(memberId)){
            throw new AchangException(20001,"请登录");
        }

        //生成订单，并生成对应的订单号
        String orderNo = tOrderService.createOrders(courseId,memberId);

        return R.ok().data("orderNo",orderNo);
    }

    //根据订单号查询订单信息
    @GetMapping("/getOrderInfoById/{orderNo}")
    public R getOrderInfoById(@PathVariable String orderNo){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder tOrder = tOrderService.getOne(wrapper);
        return R.ok().data("item",tOrder);
    }

    //根据【用户id、课程id】查询订单表中的状态
    @GetMapping("/isBuyCourse/{memberId}/{courseId}")
    public Boolean isBuyCourse(@PathVariable("memberId") String memberId,@PathVariable("courseId") String courseId){
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.eq("member_id",memberId);
        wrapper.eq("status",1);//支付状态 【1】代表已支付
        int result = tOrderService.count(wrapper);

        if (result>0){//已支付
            return true;
        }else {
            return false;
        }
    }

}

