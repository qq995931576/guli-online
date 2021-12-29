package com.achang.msmservice.service.impl;

import com.achang.msmservice.service.MsmService;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

/******
 @author 阿昌
 @create 2021-03-05 13:38
 *******
 */
@Service
public class MsmServiceImpl implements MsmService {

    //发送短信的方法
    @Override
    public boolean sendMsm(HashMap<String, Object> map, String phone) {
        if (StringUtils.isEmpty(phone))return false;
        //参数1：地域节点
        //参数2：AccessKey ID
        //参数3：AccessKey Secret
        DefaultProfile profile = DefaultProfile.getProfile("default", "LTAI421A1231RNwLuu", "yn2sD31FLdcR3323dwoKaf4o");
        DefaultAcsClient client = new DefaultAcsClient(profile);

        //设置相关固定参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setSysMethod(MethodType.POST); //提交方式，默认不能改
        request.setSysDomain("dysmsapi.aliyuncs.com");//请求阿里云哪里，默认不能改
        request.setSysVersion("2017-05-25");//版本号
        request.setSysAction("SendSms");//请求哪个方法

        //设置发送相关参数
        request.putQueryParameter("PhoneNumbers",phone);//设置要发送的【手机号】
        request.putQueryParameter("SignName","阿昌日语在线教育网站");//申请阿里云短信服务的【签名名称】
        request.putQueryParameter("TemplateCode","SMS_212711286");//申请阿里云短信服务的【模版中的 模版CODE】

        //要求传递的code验证码为jason格式，可以使用JSONObject.toJSONString()将map转为json格式
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(map));

        //最终发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
            return false;
        }

    }

}
