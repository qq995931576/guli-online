package com.achang.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/******
 @author 阿昌
 @create 2021-03-04 13:32
 *******
 */
@SpringBootApplication
@ComponentScan("com.achang")
@MapperScan("com.achang.cmsservice.mapper")
public class Service_cms_Main8004 {
    public static void main(String[] args) {
        SpringApplication.run(Service_cms_Main8004.class,args);
    }
}
