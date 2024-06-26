package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.LeaveInfo;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.LeaveInfoRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveInfoService {
    @Autowired
    LeaveInfoRepository leaveInfoRepository;
    @Autowired
    StudentService studentService;

//    检查信息完整
    public boolean checkInfo(LeaveInfo leaveInfo){
        return DataUtil.checkInfo(leaveInfo);
    }

//    增加或修改
    public DataResponse addAndUpdLeaveInfo(LeaveInfo leaveInfo){

        DataResponse dataResponse=studentService.existStudentById(leaveInfo.getStudent());
        if(dataResponse.getCode()!=200||!(dataResponse.getData() instanceof Student))return dataResponse;
        else leaveInfo.setStudent((Student) dataResponse.getData());

        if(!checkInfo(leaveInfo))return DataResponse.failure(401,"信息不完整！");
        leaveInfoRepository.saveAndFlush(leaveInfo);
        return DataResponse.okM("添加成功！");
    }

//    根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        leaveInfoRepository.deleteById(id);
        return DataResponse.okM("删除成功！");
    }

//    根据学生id删除
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        leaveInfoRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

//    根据学生id查找
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<LeaveInfo> listA=leaveInfoRepository.findLeaveInfosByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

//    根据请假状态查找
    public DataResponse findByLeaveStatus(String leaveStatus){
        if(leaveStatus==null)return DataResponse.failure(401,"信息不完整！");
        List<LeaveInfo> list=leaveInfoRepository.findLeaveInfosByLeaveStatus(leaveStatus);
        if(list==null)return DataResponse.failure(404,"未找到相关的信息");
        return DataResponse.success(list);
    }

//    获取所有信息
    public DataResponse findAll(){
        return DataResponse.success(leaveInfoRepository.findAll());
    }
}
