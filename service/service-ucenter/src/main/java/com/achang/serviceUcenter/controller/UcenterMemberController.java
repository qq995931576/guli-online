package com.achang.serviceUcenter.controller;


import com.achang.commonutils.JwtUtils;
import com.achang.commonutils.R;
import com.achang.commonutils.vo.UcenterMemberVo;
import com.achang.serviceUcenter.entity.UcenterMember;
import com.achang.serviceUcenter.entity.Vo.LoginVo;
import com.achang.serviceUcenter.entity.Vo.RegisterVo;
import com.achang.serviceUcenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-03-05
 */
@RestController
    @RequestMapping("/serviceUcenter/ucenter-member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    //登录
    @PostMapping("/login")
    public R login(@RequestBody LoginVo loginVo){
        //返回token，使用jwt生成
        String token = ucenterMemberService.login(loginVo);
        return R.ok().data("token",token);
    }

    //注册
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("/getUserInfoForJwt")
    public R getUserInfoForJwt(HttpServletRequest request){
        //调用jwt工具类里面的根据request对象，获取头信息，返回用户id
        String id = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库，根据用户id，获取用户信息
        UcenterMember member = ucenterMemberService.getById(id);

        return R.ok().data("userInfo",member);
    }

    //根据用户id查询用户信息
    @PostMapping("/getMemberInfoById/{memberId}")
    public UcenterMemberVo getMemberInfoById(@PathVariable String memberId){
        UcenterMember member = ucenterMemberService.getById(memberId);
        UcenterMemberVo memberVo = new UcenterMemberVo();
        BeanUtils.copyProperties(member,memberVo);

        return memberVo;
    }


    //根据日期，获取那天注册人数
    @GetMapping("/countRegister/{day}")
    public R countRegister(@PathVariable String day){
        Integer count = ucenterMemberService.getCountRegister(day);
        return R.ok().data("countRegister",count);
    }


}

