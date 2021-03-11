package com.achang.serviceUcenter.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/******
 @author 阿昌
 @create 2021-03-06 14:40
 *******
 */
@Component
//@PropertySource("classpath:application.properties")
public class ConstantProperties implements InitializingBean {

    @Value("${wx.open.app_id}")
    private String appID;

    @Value("${wx.open.app_secret}")
    private String appSecret;

    @Value("${wx.open.redirect_url}")
    private String redirectUrl;

    public static String WX_APP_ID;
    public static String WX_APP_SECRET;
    public static String WX_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        WX_APP_ID=this.appID;
        WX_APP_SECRET=this.appSecret;
        WX_REDIRECT_URL=this.redirectUrl;
    }

}
