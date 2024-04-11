package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.BeforeUniversity;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.BeforeUniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeforeUniversityService {

    @Autowired
    BeforeUniversityRepository beforeUniversityRepository;


    //检查有没有没填的信息
    public boolean checkInfo(BeforeUniversity beforeUniversity){
        if(beforeUniversity==null){
            return false;
        }
        else if(beforeUniversity.getStudent()==null){
            return false;
        }
        else if(beforeUniversity.getGraduatedProvince()==null){
            return false;
        }
        else if(beforeUniversity.getGraduatedSchool()==null){
            return false;
        }
        return true;
    }

    //增加或者修改数据
    public DataResponse addAndUpdBeforeUniversity(BeforeUniversity beforeUniversity){
        if(!checkInfo(beforeUniversity))return DataResponse.failure(401,"信息不完整！");
        beforeUniversityRepository.saveAndFlush(beforeUniversity);
        return DataResponse.ok();
    }

    //删除数据
    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        beforeUniversityRepository.deleteByStudentId(id);
        return DataResponse.ok();
    }

    //查找某个学生的数据
    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        BeforeUniversity b=beforeUniversityRepository.findBeforeUniversityByStudent_StudentId(id);
        if(b==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(b);
    }

    //获得所有数据
    public DataResponse findAll(){
        return DataResponse.success(beforeUniversityRepository.findAll());
    }

}
