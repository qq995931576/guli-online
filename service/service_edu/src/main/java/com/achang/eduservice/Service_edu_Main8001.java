package com.achang.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/******
 @author 阿昌
 @create 2021-02-23 20:23
 *******
 */
@SpringBootApplication
@EnableDiscoveryClient //服务发现功能
@EnableFeignClients
@ComponentScan(basePackages = "com.achang")
public class Service_edu_Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Service_edu_Main8001.class,args);
    }
}
