package com.achang.eduservice.client;

import com.achang.commonutils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/******
 @author 阿昌
 @create 2021-03-08 16:02
 *******
 */
@Component
@FeignClient(name = "service-ucenter")
public interface ServiceUcenterClient {

    //根据用户id，获取用户信息
    @PostMapping("/serviceUcenter/ucenter-member/getMemberInfoById/{memberId}")
    public UcenterMemberVo getMemberInfoById(@PathVariable("memberId") String memberId);

}
