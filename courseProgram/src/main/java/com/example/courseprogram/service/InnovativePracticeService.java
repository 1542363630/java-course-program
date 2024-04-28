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

    public boolean checkInfo(InnovativePractice innovativePractice){
        return DataUtil.checkInfo(innovativePractice);
    }

    public DataResponse addAndUpdInnovativePractice(InnovativePractice innovativePractice){
        if(!checkInfo(innovativePractice))return DataResponse.failure(401,"信息不完整！");
        innovativePracticeRepository.saveAndFlush(innovativePractice);
        return DataResponse.ok();
    }

    public DataResponse deleteByStudentId(Integer id){

        if(id==null)return DataResponse.failure(401,"信息不完整！");
        innovativePracticeRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<InnovativePractice> listA=innovativePracticeRepository.findInnovativePracticesByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    public DataResponse findAll(){
        return DataResponse.success(innovativePracticeRepository.findAll());
    }
}
