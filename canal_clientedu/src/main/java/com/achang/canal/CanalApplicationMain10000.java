package com.achang.canal;

import com.achang.canal.client.CanalClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/******
 @author 阿昌
 @create 2021-03-10 12:02
 *******
 */
@SpringBootApplication
public class CanalApplicationMain10000 implements CommandLineRunner {

    @Autowired
    private CanalClient canalClient;

    public static void main(String[] args) {
        SpringApplication.run(CanalApplicationMain10000.class,args);
    }

    //只要程序在执行状态，他就会一直执行里面的方法
    @Override
    public void run(String... args) throws Exception {
        //项目启动，执行canal客户端监听
        canalClient.run();
    }
}
