package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.HonorInfo;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.HonorInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HonorInfoService {
    @Autowired
    HonorInfoRepository honorInfoRepository;

    public boolean checkInfo(HonorInfo honorInfo){
        if(honorInfo==null){
            return false;
        }
        else if(honorInfo.getStudent()==null){
            return false;
        }
        else if(honorInfo.getHonorFrom()==null){
            return false;
        }
        else if(honorInfo.getHonorReason()==null){
            return false;
        }
        else if(honorInfo.getHonorName()==null){
            return false;
        }
        else if(honorInfo.getHonorTime()==null){
            return false;
        }
        else if(honorInfo.getProveInfo()==null){
            return false;
        }
        else if(honorInfo.getLevel()==null){
            return false;
        }
        return true;
    }

    public DataResponse addAndUpdHonorInfo(HonorInfo honorInfo){
        if(!checkInfo(honorInfo))return DataResponse.failure(401,"信息不完整！");
        honorInfoRepository.saveAndFlush(honorInfo);
        return DataResponse.ok();
    }

    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        honorInfoRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<HonorInfo> listA=honorInfoRepository.findHonorInfosByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    public DataResponse findAll(){
        return DataResponse.success(honorInfoRepository.findAll());
    }
}
