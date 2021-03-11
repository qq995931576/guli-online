package com.achang.eduservice.service;

import com.achang.eduservice.entity.EduSubject;
import com.achang.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author achang
 * @since 2021-02-27
 */
public interface EduSubjectService extends IService<EduSubject> {

    //添加课程分类
    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneTwoSubject();

}
