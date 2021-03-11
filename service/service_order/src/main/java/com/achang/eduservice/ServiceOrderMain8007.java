package com.achang.eduservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

/******
 @author 阿昌
 @create 2021-03-08 15:45
 *******
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.achang.eduservice.mapper")
@ComponentScan("com.achang")
@EnableDiscoveryClient //服务发现功能
public class ServiceOrderMain8007 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderMain8007.class,args);
    }
}
