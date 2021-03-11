package com.achang.eduservice.client;

import com.achang.commonutils.vo.UcenterMemberVo;
import com.achang.eduservice.client.impl.UcenterClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/******
 @author 阿昌
 @create 2021-03-08 12:15
 *******
 */
@Component
@FeignClient(name = "service-ucenter",fallback = UcenterClientImpl.class)
public interface UcenterClient {

    @PostMapping("/serviceUcenter/ucenter-member/getMemberInfoById/{memberId}")
    public UcenterMemberVo getMemberInfoById(@PathVariable("memberId") String memberId);

}
