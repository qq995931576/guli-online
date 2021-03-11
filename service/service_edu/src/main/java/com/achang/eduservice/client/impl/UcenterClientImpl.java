package com.achang.eduservice.client.impl;

import com.achang.commonutils.vo.UcenterMemberVo;
import com.achang.eduservice.client.UcenterClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/******
 @author 阿昌
 @create 2021-03-08 12:15
 *******
 */
@Component
public class UcenterClientImpl implements UcenterClient {
    @Override
    public UcenterMemberVo getMemberInfoById(String memberId) {
        return null;
    }
}
