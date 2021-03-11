package com.achang.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/******
 @author 阿昌
 @create 2021-03-05 13:40
 *******
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
@ComponentScan("com.achang")
public class ServiceMsmMain8005 {
    public static void main(String[] args) {
        SpringApplication.run(ServiceMsmMain8005.class,args);
    }
}
