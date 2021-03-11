package com.achang.servicebase.exceptionHandler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/******
 @author 阿昌
 @create 2021-02-24 17:10
 *******
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AchangException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
}
