package com.achang.eduservice.service.impl;

import com.achang.eduservice.entity.EduSubject;
import com.achang.eduservice.entity.excel.SubjectData;
import com.achang.eduservice.entity.subject.OneSubject;
import com.achang.eduservice.entity.subject.TwoSubject;
import com.achang.eduservice.listener.SubjectExcelListener;
import com.achang.eduservice.mapper.EduSubjectMapper;
import com.achang.eduservice.service.EduSubjectService;
import com.achang.servicebase.exceptionHandler.AchangException;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author achang
 * @since 2021-02-27
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //添加课程分类
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            //文件输入流
            InputStream is = file.getInputStream();

            //调用方法进行读取
            EasyExcel.read(is, SubjectData.class,new SubjectExcelListener(eduSubjectService))
                    .sheet().doRead();

        }catch (Exception e){
            e.printStackTrace();
            throw new AchangException(20002,"添加课程分类失败");
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询所有一级分类   parent_id = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> oneSubjectsList = baseMapper.selectList(wrapperOne);

        //查询所有二级分类  parent_id != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper();
        wrapperTwo.ne("parent_id","0");
        List<EduSubject> twoSubjectsList = baseMapper.selectList(wrapperTwo);

        //创建list集合，用于封装最终数据
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值
        //封装到要求的最终list集合中
        for (int i = 0; i < oneSubjectsList.size(); i++) { //遍历oneSubjectList集合
            //得到oneSubjectsList中每个eduSubject对象
            EduSubject oSubject = oneSubjectsList.get(i);

            //把eduSubject里面值获取出来，放到oneSubject对象中去
            OneSubject oneSubject = new OneSubject();

//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            //把eduSubject值复制到oneSubject中去【要求两个类的复制注入的属性名一致】
            BeanUtils.copyProperties(oSubject,oneSubject);

            //多个OneSubject放到finalSubjectList里面
            finalSubjectList.add(oneSubject);


            //在一级分类循环遍历查询所有的二级分类
            //创建list集合封装每个一级分类的二级分类
            ArrayList<TwoSubject> finalTwoSubjects = new ArrayList<>();

            //遍历二级list集合
            for (int j = 0; j < twoSubjectsList.size(); j++) {
                //获取每个二级分类
                EduSubject tSubject = twoSubjectsList.get(j);
                //判断二级分类parentid和一级分类id是否一样
                if (tSubject.getParentId().equals(oSubject.getId())){
                    //把tSubject值复制到TwoSubject，最终放在twoSubjectsList中
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject,twoSubject);
                    finalTwoSubjects.add(twoSubject);
                }
            }

            //把一级下面所有二级分类放到oneSubject里面
            oneSubject.setChildren(finalTwoSubjects);


        }

        //封装二级分类


        return finalSubjectList;
    }
}
