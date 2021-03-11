package com.achang.servicebase.exceptionHandler;


import com.achang.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/******
 @author 阿昌
 @create 2021-02-24 16:43
 *******
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //指定出现什么异常会执行这个方法
    @ExceptionHandler(Exception.class)
    //因为他不在Controller中。没有@RestController，所以数据不会返回，需要加@ResponeseBody返回数据
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理。。。");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("执行了ArithmeticException异常");
    }

    @ExceptionHandler(AchangException.class)
    @ResponseBody
    public R error(AchangException e){
        e.printStackTrace();;
        log.error(e.getMessage());
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
