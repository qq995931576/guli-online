package com.achang.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/******
 @author 阿昌
 @create 2021-02-27 12:06
 *******
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan({"com.achang"}) //扫描service_base内容
public class OssApplicationMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(OssApplicationMain8002.class,args);
    }
}
