package com.achang.eduservice.entity.chapter;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/******
 @author 阿昌
 @create 2021-03-01 13:10
 *******
 */
@Data
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    //表示小节
    private List<VideoVo> children = new ArrayList<VideoVo>();

}
