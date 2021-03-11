package com.achang.eduservice.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/******
 @author 阿昌
 @create 2021-02-27 18:58
 *******
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        //1、设置写入文件夹地址和excel文件名称
        String filename="C:\\Users\\PePe\\Desktop\\DemoData2.xlsx";

        //调用easyExcel里面的方法实现写操作
        //参数1：文件名称
        //参数2：对应实体类
//        EasyExcel
//                .write(filename,DemoData.class)
//                .sheet("学生列表")
//                .doWrite(getLists());

        ExcelWriter excelWriter = EasyExcel.write(filename, DemoData.class)
                .build();
        WriteSheet build = EasyExcel.writerSheet("写入方法2").build();
        // 这里 需要指定写用哪个class去写
        excelWriter.write(getLists(),build);
        // 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    //创建方法返回List集合
    private static List<DemoData> getLists(){
        ArrayList<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setSname("achang ："+ i);
            list.add(demoData);
        }
        return list;
    }

    @Test
    public void testReadExcel(){
        String filename="C:\\Users\\PePe\\Desktop\\DemoData2.xlsx";

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(filename,DemoData.class,new ExcelListener())
                .sheet().doRead();
    }
}
