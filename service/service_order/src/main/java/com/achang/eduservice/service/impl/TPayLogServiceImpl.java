package com.achang.eduservice.service.impl;

import com.achang.eduservice.entity.TOrder;
import com.achang.eduservice.entity.TPayLog;
import com.achang.eduservice.mapper.TPayLogMapper;
import com.achang.eduservice.service.TOrderService;
import com.achang.eduservice.service.TPayLogService;
import com.achang.eduservice.uitls.HttpClient;
import com.achang.servicebase.exceptionHandler.AchangException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author achang
 * @since 2021-03-08
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {

    @Autowired
    private TOrderService tOrderService;


    //根据订单号，生成微信支付二维码的接口
    @Override
    public Map<String, Object> createWxQrcode(String orderNo) {
        try{
            //1、根据订单号查询订单信息
            QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no",orderNo);
            TOrder tOrder = tOrderService.getOne(wrapper);
            
            //2、使用map来设置生成二维码需要的参数
            HashMap<String, String> map = new HashMap<>();
            map.put("appid","wx74862e0dfcf69954");//支付id
            map.put("mch_id", "1558950191");//商户号
            map.put("nonce_str", WXPayUtil.generateNonceStr());//生成随机的字符串，让每次生成的二维码不一样
            map.put("body", tOrder.getCourseTitle());//生成二维码的名字
            map.put("out_trade_no", orderNo);//二维码唯一的标识
            map.put("total_fee", tOrder.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");//支付金额
            map.put("spbill_create_ip", "127.0.0.1");//现在进行支付的ip地址，实际项目使用项目的域名
            map.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify");//支付后回调地址
            map.put("trade_type", "NATIVE");//支付类型，NATIVE:根据价格生成二维码

            //3、发送httpClient请求，传递参数【xml格式】，微信支付提供的固定地址
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            //参数1：要转换为xml格式的map
            //参数2：商户的key，用于加密二维码中的信息
            client.setXmlParam(WXPayUtil.generateSignedXml(map,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);//上面发送请求的是https。默认支持，需要设置为true支持
            //执行post请求发送
            client.post();

            //4、得到发送请求返回的结果
            //返回的结果是xml格式的
            String content = client.getContent();

            //把xml格式转换为map集合，他里面的数据不全
            Map<String, String> resultMap = WXPayUtil.xmlToMap(content);

            //最终返回数据封装
            HashMap hashMap = new HashMap<>();
            hashMap.put("out_trade_no",orderNo);
            hashMap.put("course_id",tOrder.getCourseId());
            hashMap.put("total_fee",tOrder.getTotalFee());
            hashMap.put("result_code",resultMap.get("result_code")); //二维码操作状态码
            hashMap.put("code_url",resultMap.get("code_url")); //二维码地址

            //微信支付二维码2小时过期，可采取2小时未支付取消订单
            //redisTemplate.opsForValue().set(orderNo, hashMap, 120,TimeUnit.MINUTES);

            return hashMap;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


    //根据订单号，查询支付的状态
    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try{


        //1、封装参数
        Map m = new HashMap<>();
        m.put("appid", "wx74862e0dfcf69954");
        m.put("mch_id", "1558950191");
        m.put("out_trade_no", orderNo);
        m.put("nonce_str", WXPayUtil.generateNonceStr());

        //2、发送httpClient请求
        HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
        client.setHttps(true);
        client.setXmlParam(WXPayUtil.generateSignedXml(m,"T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));//通过商户key加密
        client.post();

        //3、返回第三方的数据
        String xml = client.getContent();
        //4、转成Map
        Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

        //5、返回
        return resultMap;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    //向支付表中添加支付记录，并更新订单表的订单状态
    @Override
    public void updateOrderStatus(Map<String, String> map) {
        //获取前一步生成二维码中的订单号
        String orderNo = map.get("out_trade_no");
        //根据订单号，查询订单信息
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no",orderNo);
        TOrder tOrder = tOrderService.getOne(wrapper);


        //判断订单状态是否为1，为1就是支付过了
        if (tOrder.getStatus().intValue()==1){return;}

        //更新订单表中的叮当状态
        tOrder.setStatus(1);//1代表已支付
        tOrderService.updateById(tOrder);

        //向支付表里添加支付记录
        TPayLog tPayLog = new TPayLog();
        tPayLog.setOrderNo(orderNo);//支付订单号
        tPayLog.setPayTime(new Date());//支付时间
        tPayLog.setPayType(1);//支付类型
        tPayLog.setTotalFee(tOrder.getTotalFee());//总金额(分)
        tPayLog.setTradeState(map.get("trade_state"));//支付状态
        tPayLog.setTransactionId(map.get("transaction_id"));//订单流水号
        tPayLog.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(tPayLog);


    }


}
