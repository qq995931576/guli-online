package com.achang.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/******
 @author 阿昌
 @create 2021-03-10 15:42
 *******
 */
@SpringBootApplication
@EnableDiscoveryClient //服务发现
@ComponentScan(basePackages = "com.achang")
@MapperScan("com.achang.aclservice.mapper")
public class AclServiceMain8009 {
    public static void main(String[] args) {
        SpringApplication.run(AclServiceMain8009.class,args);
    }
}
