package com.achang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/******
 @author 阿昌
 @create 2021-03-10 13:43
 *******
 */
@SpringBootApplication
@EnableDiscoveryClient //服务发现
public class GateWallMain8222 {
    public static void main(String[] args) {
        SpringApplication.run(GateWallMain8222.class,args);
    }
}
