package com.achang.cmsservice.controller;


import com.achang.cmsservice.entity.CrmBanner;
import com.achang.cmsservice.entity.vo.BannerQuery;
import com.achang.cmsservice.service.CrmBannerService;
import com.achang.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-03-04
 */
//后台banner管理接口
@RestController
@RequestMapping("/cmsservice/bannerAdmin")
@CrossOrigin //解决跨域问题
public class BannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    //条件分页查询banner
    @PostMapping("/pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable Long page, @PathVariable Long limit, @RequestBody(required = false) BannerQuery bannerQuery){
        Page<CrmBanner> bannerPage = new Page<>(page,limit);

        crmBannerService.pageQuery(bannerPage,bannerQuery);

        //获取数据
        List<CrmBanner> list = bannerPage.getRecords();
        //获取总记录数
        long total = bannerPage.getTotal();

        return R.ok().data("rows",list).data("total",total);
    }

    //添加banner
    @PostMapping("/addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner){
        boolean flag = crmBannerService.save(crmBanner);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //修改banner
    @PostMapping("/updateBanner")
    public R updateBanner(@RequestBody CrmBanner crmBanner){
        boolean flag = crmBannerService.updateById(crmBanner);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据id删除banner
    @DeleteMapping("/deleteBannerById/{id}")
    public R deleteBannerById(@PathVariable String id){
        boolean flag = crmBannerService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据id查询banner
    @GetMapping("/getBannerById/{id}")
    public R getBannerById(@PathVariable String id){
        CrmBanner crmBanner = crmBannerService.getById(id);
        return R.ok().data("item",crmBanner);
    }


}

