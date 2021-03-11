package com.achang.cmsservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/******
 @author 阿昌
 @create 2021-03-04 14:28
 *******
 */
@Data
public class BannerQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    //幻灯片名字
    private String name;

    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    private String end;
}
