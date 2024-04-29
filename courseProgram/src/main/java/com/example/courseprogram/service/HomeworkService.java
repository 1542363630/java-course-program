package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Homework;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.HomeworkRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkService {
    @Autowired
    HomeworkRepository homeworkRepository;

    //检查信息是否完整
    public boolean checkInfo(Homework homework){
        return DataUtil.checkInfo(homework);
    }

    //增加或修改
    public DataResponse addAndUpdHomework(Homework homework){
        if(!checkInfo(homework))return DataResponse.failure(401,"信息不完整！");
        homeworkRepository.saveAndFlush(homework);
        return DataResponse.ok();
    }

    //根据学生id删除
    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        homeworkRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //根据学生id查找
    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<Homework> listA=homeworkRepository.findHomeworkByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //获取所有信息
    public DataResponse findAll(){
        return DataResponse.success(homeworkRepository.findAll());
    }
}
