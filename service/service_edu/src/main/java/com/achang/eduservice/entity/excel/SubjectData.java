package com.achang.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/******
 @author 阿昌
 @create 2021-02-27 20:24
 *******
 */
@Data
@ToString
public class SubjectData {

    //一级分类
    @ExcelProperty(index = 0)
    private String oneSubjectName;

    //二级分类
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
