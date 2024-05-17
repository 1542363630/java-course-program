package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.InnovativePractice;
import com.example.courseprogram.model.DO.InnovativePracticeStudent;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.InnovativePracticeStudentRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InnovativePracticeStudentService {
    @Autowired
    InnovativePracticeStudentRepository innovativePracticeStudentRepository;
    @Autowired
    InnovativePracticeService innovativePracticeService;
    @Autowired
    StudentService studentService;

    //检查信息完整
    public boolean checkInfo(InnovativePracticeStudent innovativePracticeStudent){
        return DataUtil.checkInfo(innovativePracticeStudent);
    }

    //增加或更改
    public DataResponse addAndUpdInnovativePracticeStudent(InnovativePracticeStudent innovativePracticeStudent){
        DataResponse dataResponse=innovativePracticeService.existInnovativePracticeById(innovativePracticeStudent.getInnovativePractice());
        if(dataResponse.getCode()!=200||!(dataResponse.getData() instanceof InnovativePractice))return dataResponse;
        else innovativePracticeStudent.setInnovativePractice((InnovativePractice) dataResponse.getData());

        dataResponse=studentService.existStudentById(innovativePracticeStudent.getStudent());
        if(dataResponse.getCode()!=200||!(dataResponse.getData() instanceof Student))return dataResponse;
        else innovativePracticeStudent.setStudent((Student) dataResponse.getData());

        if(!checkInfo(innovativePracticeStudent))return DataResponse.failure(401,"信息不完整！");
        innovativePracticeStudentRepository.saveAndFlush(innovativePracticeStudent);
        return DataResponse.ok();
    }

    //根据学生id删除
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        innovativePracticeStudentRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //根据学生id查找
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<InnovativePracticeStudent> listA=innovativePracticeStudentRepository.findInnovativePracticeStudentsByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //根据活动id删除
    public DataResponse deleteByInnovativePracticeId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        innovativePracticeStudentRepository.deleteByInnovativePractice_InnovativeId(id);
        return DataResponse.ok();
    }

    //根据活动id查找
    public DataResponse findByInnovativePracticeId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<InnovativePracticeStudent> listA=innovativePracticeStudentRepository.findInnovativePracticeStudentsByInnovativePractice_InnovativeId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该创新实践的参与人员信息");
        return DataResponse.success(listA);
    }

    //根据id删除数据
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        innovativePracticeStudentRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }

//    //根据学号和活动类型查询
//    public DataResponse findByStudentIdAndType(Long id,String type){
//        if(id==null||type==null)return DataResponse.failure(401,"信息不完整");
//        List<InnovativePracticeStudent> list=innovativePracticeStudentRepository.findByStudentIdAndType(id,type);
//        if(list==null)return DataResponse.failure(404,"未找到相关信息");
//        return DataResponse.success(list);
//    }

    //获取所有活动参与信息
    public DataResponse findAll(){
        return DataResponse.success(innovativePracticeStudentRepository.findAll());
    }

}
