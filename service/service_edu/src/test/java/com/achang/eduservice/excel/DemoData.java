package com.achang.eduservice.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/******
 @author 阿昌
 @create 2021-02-27 18:47
 *******
 */
//设置表头和添加的数据字段
@Data
@ToString
public class DemoData {

    //学生序号
    //设置excel表头名称
    //index代表excel表格字段索引
    @ExcelProperty(value = "学生序号",index = 0)
    private Integer sno;

    //学生名称
    //设置excel表头名称
    //index代表excel表格字段索引
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;

}
