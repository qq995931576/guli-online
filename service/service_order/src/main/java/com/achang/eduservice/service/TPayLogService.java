package com.achang.eduservice.service;

import com.achang.eduservice.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author achang
 * @since 2021-03-08
 */
public interface TPayLogService extends IService<TPayLog> {

    //根据订单号，生成微信支付二维码的接口
    Map<String, Object> createWxQrcode(String orderNo);

    //根据订单号，查询支付的状态
    Map<String, String> queryPayStatus(String orderNo);

    //向支付表中添加支付记录，并更新订单表的订单状态
    void updateOrderStatus(Map<String, String> map);
}
