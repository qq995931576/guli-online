package com.achang.vodtest;

import com.achang.voe.controller.VodController;
import com.achang.voe.service.serviceImpl.VodServiceImpl;
import com.sun.org.apache.bcel.internal.generic.NEW;

/******
 @author 阿昌
 @create 2021-03-07 20:29
 *******
 */
public class test {
    public static void main(String[] args) {
        VodServiceImpl vodService = new VodServiceImpl();
        String playAuth = vodService.getPlayAuth(" e43bad3bd91b474690e817bda489793d");
        System.out.println(playAuth);
    }
}
