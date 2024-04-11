package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Homework;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkService {
    @Autowired
    HomeworkRepository homeworkRepository;

    public boolean checkInfo(Homework homework){
        if(homework==null){
            return false;
        }
        else if(homework.getStudent()==null){
            return false;
        }
        else if(homework.getHomeworkScore()==null){
            return false;
        }
        else if(homework.getCourse()==null){
            return false;
        }
        else if(homework.getIsSubmit()==null){
            return false;
        }
        return true;
    }

    public DataResponse addAndUpdHomework(Homework homework){
        if(!checkInfo(homework))return DataResponse.failure(401,"信息不完整！");
        homeworkRepository.saveAndFlush(homework);
        return DataResponse.ok();
    }

    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        homeworkRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<Homework> listA=homeworkRepository.findHomeworkByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    public DataResponse findAll(){
        return DataResponse.success(homeworkRepository.findAll());
    }
}
