package com.achang.msmservice.service;

import java.util.HashMap;

/******
 @author 阿昌
 @create 2021-03-05 13:38
 *******
 */
public interface MsmService {

    //发送短信的方法
    boolean sendMsm(HashMap<String, Object> map, String phone);
}
