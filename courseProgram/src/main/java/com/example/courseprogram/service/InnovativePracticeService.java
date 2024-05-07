package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.InnovativePractice;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.InnovativePracticeRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InnovativePracticeService {
    @Autowired
    InnovativePracticeRepository innovativePracticeRepository;

//    检查信息完整
    public boolean checkInfo(InnovativePractice innovativePractice){
        return DataUtil.checkInfo(innovativePractice);
    }

//    增加或修改
    public DataResponse addAndUpdInnovativePractice(InnovativePractice innovativePractice){
        if(!checkInfo(innovativePractice))return DataResponse.failure(401,"信息不完整！");
        innovativePracticeRepository.saveAndFlush(innovativePractice);
        return DataResponse.ok();
    }

//    根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        innovativePracticeRepository.deleteById(id);
        return DataResponse.okM("删除成功！");
    }

//    根据学生id删除
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        innovativePracticeRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

//    根据学生id查找
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<InnovativePractice> listA=innovativePracticeRepository.findInnovativePracticesByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

//    根据类别查找
    public DataResponse findByType(String type){
        if(type==null)return DataResponse.failure(401,"信息不完整");
        List<InnovativePractice> list=innovativePracticeRepository.findInnovativePracticesByType(type);
        if(list==null)return DataResponse.failure(404,"未找到相关信息");
        return DataResponse.success(list);
    }

//    获取所有信息
    public DataResponse findAll(){
        return DataResponse.success(innovativePracticeRepository.findAll());
    }
}
