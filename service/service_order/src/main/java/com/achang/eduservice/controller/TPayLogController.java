package com.achang.eduservice.controller;


import com.achang.commonutils.R;
import com.achang.eduservice.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-03-08
 */
@RestController
@RequestMapping("/eduorder/t-pay-log")
@CrossOrigin
public class TPayLogController {

    @Autowired
    private TPayLogService tPayLogService;

    //根据订单号，生成微信支付二维码的接口
    @GetMapping("/createWxQRcode/{orderNo}")
    public R createWxQRcode(@PathVariable String orderNo){
        //返回信息，包含二维码地址、其他信息
        Map<String, Object> map = tPayLogService.createWxQrcode(orderNo);
        return R.ok().data(map);
    }






    //根据订单号查询订单支付状态
    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){

        Map<String,String> map = tPayLogService.queryPayStatus(orderNo);
        if (map==null){
            return R.error().message("支付出错了");
        }
        //如果返回的map不为空，通过map获取订单的状态
        if (map.get("trade_state").equals("SUCCESS")){ //支付成功
            //添加记录到支付表里，并更新订单表的状态
            tPayLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }

        return R.ok().message("支付中").code(25000);
    }



}

