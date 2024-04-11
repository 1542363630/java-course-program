package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Fee;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {
    @Autowired
    FeeRepository feeRepository;


    //检查有没有没填的信息
    public boolean checkInfo(Fee fee){
        if(fee==null){
            return false;
        }
        else if(fee.getStudent()==null){
            return false;
        }
        else if(fee.getDay()==null){
            return false;
        }
        else if(fee.getMoney()==null){
            return false;
        }
        return true;
    }

    //增加或者修改数据
    public DataResponse addAndUpdFee(Fee fee){
        if(!checkInfo(fee))return DataResponse.failure(401,"信息不完整！");
        feeRepository.saveAndFlush(fee);
        return DataResponse.ok();
    }

    //删除数据
    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        feeRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //查找某个学生的数据
    public DataResponse findByStudentId(Integer id){
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
