package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.*;
import com.example.courseprogram.model.DO.InnovativePractice;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.DailyActivityStudentRepository;
import com.example.courseprogram.repository.InnovativePracticeRepository;
import com.example.courseprogram.repository.InnovativePracticeStudentRepository;
import com.example.courseprogram.repository.StudentRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InnovativePracticeService {
    @Autowired
    InnovativePracticeRepository innovativePracticeRepository;
    @Autowired
    InnovativePracticeStudentRepository innovativePracticeStudentRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    //检查信息完整
    public boolean checkInfo(InnovativePractice innovativePractice){
        return DataUtil.checkInfo(innovativePractice);
    }

    //检查创新实践是否存在
    public DataResponse existInnovativePracticeById(InnovativePractice innovativePractice){
        if(innovativePractice==null||innovativePractice.getInnovativeId()==null)return DataResponse.failure(401,"信息不完整！");
        Optional<InnovativePractice> opInnovativePractice=innovativePracticeRepository.findById(innovativePractice.getInnovativeId());
        //noinspection OptionalIsPresent
        if(opInnovativePractice.isPresent()){
            return DataResponse.success(opInnovativePractice.get());
        }
        else{
            return DataResponse.failure(404,"未找到该活动！");
        }
    }
    
    //增加或修改
    public DataResponse addAndUpdInnovativePractice(InnovativePractice innovativePractice, List<Student> students){
        if(!checkInfo(innovativePractice))return DataResponse.failure(401,"信息不完整！");
        innovativePracticeRepository.saveAndFlush(innovativePractice);
        String msg="";
        for(Student student:students){
            int index=students.indexOf(student);
            DataResponse dataResponse=studentService.existStudentById(student);
            msg=msg+"\n"+"第"+index+"个：";
            if(dataResponse.getCode()!=200){
                msg=msg+dataResponse.getMessage();
            }
            else if(!(dataResponse.getData() instanceof Student)){
                msg=msg+"不是学生实例";
            }
            else if(innovativePracticeStudentRepository.selectByIdAndStudentId(innovativePractice.getInnovativeId(),student.getStudentId())!=0){
                msg=msg+"该学生已存在";
            }
            else{
                student=(Student) dataResponse.getData();
                InnovativePracticeStudent innovativePracticeStudent=new InnovativePracticeStudent(null,student,innovativePractice);
//                students.set(index,);
                innovativePracticeStudentRepository.saveAndFlush(innovativePracticeStudent);
                msg=msg+"添加成功";
            }
        }
        return DataResponse.okM(msg);
    }

    //根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        innovativePracticeRepository.deleteById(id);
        return DataResponse.okM("删除成功！");
    }

    //根据学生id查找
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<InnovativePractice> listA=innovativePracticeRepository.findInnovativePracticesByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //根据类别查找
    public DataResponse findByType(String type){
        if(type==null)return DataResponse.failure(401,"信息不完整");
        List<InnovativePractice> list=innovativePracticeRepository.findInnovativePracticesByType(type);
        if(list==null)return DataResponse.failure(404,"未找到相关信息");
        return DataResponse.success(list);
    }

    //根据学号和活动类型查询
    public DataResponse findByStudentIdAndType(Long id,String type){
        if(id==null||type==null)return DataResponse.failure(401,"信息不完整");
        List<DailyActivity> list=innovativePracticeRepository.findByStudentIdAndType(id,type);
        if(list==null)return DataResponse.failure(404,"未找到相关信息");
        return DataResponse.success(list);
    }

    //获取所有信息
    public DataResponse findAll(){
        return DataResponse.success(innovativePracticeRepository.findAll());
    }
}
