package com.achang.cmsservice.service.impl;

import com.achang.cmsservice.entity.CrmBanner;
import com.achang.cmsservice.entity.vo.BannerQuery;
import com.achang.cmsservice.mapper.CrmBannerMapper;
import com.achang.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author achang
 * @since 2021-03-04
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    //查询所有banner
    @Override
    @Cacheable(key = "'selectIndexList'",value = "banner")
    public List<CrmBanner> getAllBanner() {
        //根据id进行降序排序，显示排序之后的前两条结果
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");

        List<CrmBanner> list = baseMapper.selectList(wrapper);
        return list;
    }

    //多条件带分页查询
    @Override
    public void pageQuery(Page<CrmBanner> bannerPage, BannerQuery bannerQuery) {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();

        if (bannerQuery!=null){
            String name = bannerQuery.getName();
            String begin = bannerQuery.getBegin();
            String end = bannerQuery.getEnd();

            if (!StringUtils.isEmpty(name)){
                wrapper.like("title",name);
            }
            if (!StringUtils.isEmpty(begin)){
                wrapper.ge("gmt_create",begin);
            }
            if (!StringUtils.isEmpty(end)){
                wrapper.le("gmt_modified",end);
            }
        }

        //排序
        wrapper.orderByDesc("gmt_create");

        //带上门判断后的条件进行分页查询
        baseMapper.selectPage(bannerPage, wrapper);
    }

}
