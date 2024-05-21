package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Homework;
import com.example.courseprogram.model.DO.HomeworkInfo;
import com.example.courseprogram.model.DO.SelectedCourseInfo;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.HomeworkInfoRepository;
import com.example.courseprogram.repository.HomeworkRepository;
import com.example.courseprogram.repository.StudentRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeworkService {
    @Autowired
    HomeworkRepository homeworkRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    HomeworkInfoRepository homeworkInfoRepository;

    //检查信息是否完整
    public boolean checkInfo(Homework homework){
        return DataUtil.checkInfo(homework);
    }

    //增加或修改
    public DataResponse addAndUpdHomework(Homework homework){
        Student s=homework.getStudent();
        if(s==null||s.getStudentId()==null)return DataResponse.failure(401,"信息不完整！");
        Optional<Student> opStudent=studentRepository.findById(s.getStudentId());
        if(opStudent.isPresent()){
            s=opStudent.get();
            homework.setStudent(s);
        }
        else{
            return DataResponse.failure(404,"未找到该学生！");
        }

        HomeworkInfo homeworkInfo=homework.getHomeworkInfo();
        if(homeworkInfo==null||homeworkInfo.getHomeworkInfoId()==null)return DataResponse.failure(401,"信息不完整！");
        Optional<HomeworkInfo> opHomeworkInfo=homeworkInfoRepository.findById(homeworkInfo.getHomeworkInfoId());
        if(opHomeworkInfo.isPresent()){
            homeworkInfo=opHomeworkInfo.get();
            homework.setHomeworkInfo(homeworkInfo);
        }
        else{
            return DataResponse.failure(404,"未找到该作业信息！");
        }
        if(!checkInfo(homework))return DataResponse.failure(401,"信息不完整！");
        homeworkRepository.saveAndFlush(homework);
        return DataResponse.ok();
    }

    //根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        homeworkRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }

    //根据学生id删除
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        homeworkRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //根据学生id查找
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<Homework> listA=homeworkRepository.findHomeworkByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //根据作业信息id找作业
    public DataResponse findByHomeworkInfoId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整");
        List<Homework> list=homeworkRepository.findHomeworkByHomeworkInfo_HomeworkInfoId(id);
        if(list==null)return DataResponse.failure(404,"未找到相关信息");
        return DataResponse.success(list);
    }

    //根据课程编号或名称查询
    public DataResponse findByCourseNumberOrName(String numName){
        if (numName==null){
            return DataResponse.failure(401,"信息不完整");
        }
        else {
            List<Homework> list = homeworkRepository.findByCourseNumberOrName(numName);
            if(list.isEmpty()){
                return DataResponse.failure(404,"未找到相关信息");
            }
            else {
                return DataResponse.success(list);
            }
        }
    }

    //获取所有信息
    public DataResponse findAll(){
        return DataResponse.success(homeworkRepository.findAll());
    }
}
