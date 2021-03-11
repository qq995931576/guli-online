package com.achang.eduservice.controller;

import com.achang.commonutils.R;
import org.springframework.web.bind.annotation.*;

/******
 @author 阿昌
 @create 2021-02-26 13:11
 *******
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin //解决跨域问题
public class EduLoginController {

    //login
    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    //info
    @GetMapping("/info")
    public R info(){
        return R.ok().data("roles","admin").data("name","阿昌").data("avatar","http://www.weixintouxiang.cn/weixin/20140607090832328.gif");
    }

}
