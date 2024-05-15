package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Fee;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.FeeRepository;
import com.example.courseprogram.repository.StudentRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeeService {
    @Autowired
    FeeRepository feeRepository;

    @Autowired
    StudentRepository studentRepository;

    //检查有没有没填的信息
    public boolean checkInfo(Fee fee){
        return DataUtil.checkInfo(fee);
    }

    //增加或者修改数据
    public DataResponse addAndUpdFee(Fee fee){
        Student s=fee.getStudent();
        if(s==null||s.getStudentId()==null)return DataResponse.failure(401,"信息不完整！");
        Optional<Student> opStudent=studentRepository.findById(s.getStudentId());
        if(opStudent.isPresent()){
            s=opStudent.get();
            fee.setStudent(s);
        }
        else{
            return DataResponse.failure(404,"未找到该学生！");
        }

        if(!checkInfo(fee))return DataResponse.failure(401,"信息不完整！");
        feeRepository.saveAndFlush(fee);
        return DataResponse.ok();
    }

    //根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整");
        feeRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }

    //删除数据
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        feeRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //查找某个学生的数据
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<Fee> b=feeRepository.findFeesByStudent_StudentId(id);
        if(b==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(b);
    }

    //获得所有数据
    public DataResponse findAll(){
        return DataResponse.success(feeRepository.findAll());
    }
}
