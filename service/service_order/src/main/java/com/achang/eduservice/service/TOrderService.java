package com.achang.eduservice.service;

import com.achang.eduservice.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author achang
 * @since 2021-03-08
 */
public interface TOrderService extends IService<TOrder> {

    //生成订单的方法
    String createOrders(String courseId, String memberId);
}
