package com.achang.cmsservice.controller;

import com.achang.cmsservice.entity.CrmBanner;
import com.achang.cmsservice.service.CrmBannerService;
import com.achang.commonutils.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/******
 @author 阿昌
 @create 2021-03-04 13:41
 *******
 */

@CrossOrigin //解决跨域问题
@RestController
@RequestMapping("/cmsservice/bannerFront")
public class BannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    //查询所有幻灯片
    @GetMapping("getAll")
    public R getAll(){
        List<CrmBanner> list = crmBannerService.getAllBanner();
        return R.ok().data("list",list);
    }


}
