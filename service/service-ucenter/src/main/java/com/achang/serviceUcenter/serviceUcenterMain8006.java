package com.achang.serviceUcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;

/******
 @author 阿昌
 @create 2021-03-05 16:02
 *******
 */
@SpringBootApplication//取消数据源自动配置 (exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.achang")
@MapperScan("com.achang.serviceUcenter.mapper")
@EnableDiscoveryClient //开启服务发现
public class serviceUcenterMain8006 {
    public static void main(String[] args) {
        SpringApplication.run(serviceUcenterMain8006.class,args);
    }
}
