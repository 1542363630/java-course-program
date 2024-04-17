package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.LeaveInfo;
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

    public boolean checkInfo(LeaveInfo leaveInfo){
        return DataUtil.checkInfo(leaveInfo);
    }

    public DataResponse addAndUpdLeaveInfo(LeaveInfo leaveInfo){
        if(!checkInfo(leaveInfo))return DataResponse.failure(401,"信息不完整！");
        leaveInfoRepository.saveAndFlush(leaveInfo);
        return DataResponse.ok();
    }

    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        leaveInfoRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<LeaveInfo> listA=leaveInfoRepository.findLeaveInfosByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    public DataResponse findAll(){
        return DataResponse.success(leaveInfoRepository.findAll());
    }
}
