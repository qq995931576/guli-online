package com.achang.serviceUcenter.mapper;

import com.achang.serviceUcenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author achang
 * @since 2021-03-05
 */

public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer getCountRegister(String day);

}
