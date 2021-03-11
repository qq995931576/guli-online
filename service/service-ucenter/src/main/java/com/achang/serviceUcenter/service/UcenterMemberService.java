package com.achang.serviceUcenter.service;

import com.achang.serviceUcenter.entity.UcenterMember;
import com.achang.serviceUcenter.entity.Vo.LoginVo;
import com.achang.serviceUcenter.entity.Vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author achang
 * @since 2021-03-05
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //登录的方法
    String login(LoginVo loginVo);

    //注册的方法
    void register(RegisterVo registerVo);

    //根据微信id获取用户数据
    UcenterMember getMemberByOpenId(String openid);

    //根据日期，获取那天注册人数
    Integer getCountRegister(String day);
}
