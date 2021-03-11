package com.achang.voe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/******
 @author 阿昌
 @create 2021-03-02 19:20
 *******
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.achang"}) //用于扫描swagger
@EnableDiscoveryClient //服务发现功能
public class Service_vod_Main8003 {
    public static void main(String[] args) {
        SpringApplication.run(Service_vod_Main8003.class,args);
    }
}
