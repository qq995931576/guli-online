package com.achang.eduservice.controller;


import com.achang.commonutils.R;
import com.achang.eduservice.entity.EduTeacher;
import com.achang.eduservice.entity.vo.TeacherQuery;
import com.achang.eduservice.service.EduTeacherService;
import com.achang.servicebase.exceptionHandler.AchangException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author achang
 * @since 2021-02-23
 */
@RestController
@CrossOrigin//解决跨域问题
@RequestMapping("/eduservice/edu-teacher")
@Api(description="讲师管理")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    //查询讲师表所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("/findAll")
    public R list(){
        List<EduTeacher> list = eduTeacherService.list(null);


//        try {
//            int i = 10/0;
//        } catch (Exception e) {
//            throw new AchangException(20001,"执行了AchangException自定义异常处理");
//        }


        return R.ok().data("items",list);
    }

    //逻辑删除讲师
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("/deleteTeacherById/{id}")
    public R deleteTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true)@PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //分页查询
    //page：当前页
    //limit：每页显示记录数
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("/pageList/{page}/{limit}")
    public R pageList(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
                      @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit
                      ){
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        //分页查询，查完后，会将数据封装在pageParam中
        eduTeacherService.page(pageParam,null);
        //获取查询到的数据
        List<EduTeacher> records = pageParam.getRecords();
        //获取总记录数
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }


    //多条件查询讲师带分页
    @ApiOperation(value = "多条件查询讲师带分页")
    @PostMapping("/pageTeacherCondition/{page}/{limit}")
    public R pageTeacherCondition(@ApiParam(name = "page", value = "当前页码", required = true)@PathVariable Long page,
                                  @ApiParam(name = "limit", value = "每页记录数", required = true)@PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){//通过封装TeacherQuery对象来直接传递查询条件
        //创建分页page对象
        Page<EduTeacher> pageParam = new Page<>(page, limit);

        //调用方法实现多条件分页查询
        eduTeacherService.pageQuery(pageParam,teacherQuery);

        //获取查询到的数据
        List<EduTeacher> records = pageParam.getRecords();

        //获取总记录数
        long total = pageParam.getTotal();
        return R.ok().data("total",total).data("rows",records);

    }

    //新增讲师
    @ApiModelProperty(value = "新增讲师")
    @PostMapping("/save")
    public R save(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.save(eduTeacher);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //根据id查询，用于信息回显
    @ApiModelProperty(value = "根据id查询")
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item",teacher);
    }

    //修改讲师
    @ApiModelProperty(value = "修改讲师")
    @PostMapping("/updateById")
    public R updateById(@RequestBody EduTeacher teacher){
        boolean flag = eduTeacherService.updateById(teacher);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }





}

