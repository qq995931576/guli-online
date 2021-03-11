package com.achang.eduservice.controller;


import com.achang.commonutils.R;
import com.achang.eduservice.entity.subject.OneSubject;
import com.achang.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-02-27
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin //解决跨域问题
@Api(description="课程分类管理")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类
    //获取上传过来的文件，把文件内容读取出来
    @PostMapping("/addSubject")
    public R addSubject(MultipartFile file){
        //获取上传的excel文件 MultipartFile
        eduSubjectService.saveSubject(file,eduSubjectService);
        //判断返回集合是否为空

        return R.ok();
    }


    //课程分类列表（树形）
    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("/getAllSubject")
    public R getAllSubject(){
        //ist集合泛型是一级分类，一级分类中本身含有二级分类
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }













}

