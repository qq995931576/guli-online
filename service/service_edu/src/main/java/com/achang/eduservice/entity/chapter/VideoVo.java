package com.achang.eduservice.entity.chapter;

import lombok.Data;

import java.io.Serializable;

/******
 @author 阿昌
 @create 2021-03-01 13:15
 *******
 */
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private Boolean free;

    private String videoSourceId;

}
