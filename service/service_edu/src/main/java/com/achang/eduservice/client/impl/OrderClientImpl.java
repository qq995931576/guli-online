package com.achang.eduservice.client.impl;

import com.achang.eduservice.client.OrderClient;
import com.achang.servicebase.exceptionHandler.AchangException;
import org.springframework.stereotype.Component;

/******
 @author 阿昌
 @create 2021-03-09 12:02
 *******
 */
@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public Boolean isBuyCourse(String memberId, String courseId) {
        return null;
    }
}
