package com.achang.cmsservice.service;

import com.achang.cmsservice.entity.CrmBanner;
import com.achang.cmsservice.entity.vo.BannerQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author achang
 * @since 2021-03-04
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getAllBanner();

    void pageQuery(Page<CrmBanner> bannerPage, BannerQuery bannerQuery);

}
