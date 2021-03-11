package com.achang.commonutils;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/******
 @author 阿昌
 @create 2021-02-24 12:54
 *******
 */
@Data
public class R {
    @ApiModelProperty("是否成功")
    private boolean success;

    @ApiModelProperty("响应码")
    private Integer code;

    @ApiModelProperty("返回信息")
    private String message;

    @ApiModelProperty("返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //无参构造方法私有
    private R() {
    }

    //成功 静态方法
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功。。。");
        return r;
    }

    //失败 静态方法
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

}
