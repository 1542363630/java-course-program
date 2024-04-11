package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.LeaveInfo;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.LeaveInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveInfoService {
    @Autowired
    LeaveInfoRepository leaveInfoRepository;

    public boolean checkInfo(LeaveInfo leaveInfo){
        if(leaveInfo==null){
            return false;
        }
        else if(leaveInfo.getStudent()==null){
            return false;
        }
        else if(leaveInfo.getLeaveReason()==null){
            return false;
        }
        else if(leaveInfo.getApprover()==null){
            return false;
        }
        else if(leaveInfo.getLeaveEndTime()==null){
            return false;
        }
        else if(leaveInfo.getLeaveBeginTime()==null){
            return false;
        }
        else if(leaveInfo.getIsParentKnow()==null){
            return false;
        }
        return true;
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
