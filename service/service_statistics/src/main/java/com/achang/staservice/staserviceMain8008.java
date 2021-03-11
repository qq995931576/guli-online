package com.achang.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/******
 @author 阿昌
 @create 2021-03-09 14:22
 *******
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.achang.staservice.mapper")
@EnableDiscoveryClient //开启服务发现
@ComponentScan("com.achang")
@EnableScheduling//开启定时任务
public class staserviceMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(staserviceMain8008.class,args);
    }
}
