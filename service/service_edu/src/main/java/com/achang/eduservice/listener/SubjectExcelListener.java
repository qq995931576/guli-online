package com.achang.eduservice.listener;

import com.achang.eduservice.entity.EduSubject;
import com.achang.eduservice.entity.excel.SubjectData;
import com.achang.eduservice.service.EduSubjectService;
import com.achang.servicebase.exceptionHandler.AchangException;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.StringUtils;

/******
 @author 阿昌
 @create 2021-02-27 20:34
 *******
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    //因为SubjectExcelListener態交给spring进行ioc管理，需要自己手动new，不能注入其他对象
    //不能实现数据库操作

    public EduSubjectService eduSubjectService;

    //有参，传递subjectService用于操作数据库
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    //无参
    public SubjectExcelListener() {
    }

    //读取excel内容，一行一行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        //表示excel中没有数据，就不需要读取了
        if (subjectData==null){
            throw new AchangException(20001,"添加失败");
        }

        //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
        //判断是否有一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (existOneSubject == null){ //没有相同的一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0"); //设置一级分类id值，0代表为一级分类
            existOneSubject.setTitle(subjectData.getOneSubjectName());//设置一级分类名
            eduSubjectService.save(existOneSubject);//给数据库添加一级分类
        }

        //获取一级分类的id值
        String parent_id = existOneSubject.getId();
        //判断是否有耳机分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), parent_id);
        if (existTwoSubject==null){//没有相同的二级分类，进行添加
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(parent_id); //设置二级分类id值
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//设置二级分类名
            eduSubjectService.save(existTwoSubject);//给数据库添加二级分类
        }

    }


    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name)
                .eq("parent_id","0");
        EduSubject oneSubject = eduSubjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService,String name,String parentId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name)
                .eq("parent_id",parentId);
        EduSubject twoSubject = eduSubjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
