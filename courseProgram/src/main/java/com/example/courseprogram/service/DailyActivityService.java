package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.DailyActivity;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.DailyActivityRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyActivityService {
    @Autowired
    DailyActivityRepository dailyActivityRepository;

    public boolean checkInfo(DailyActivity dailyActivity){
        return DataUtil.checkInfo(dailyActivity);
    }

    public DataResponse addAndUpdDailyActivity(DailyActivity dailyActivity){
        if(!checkInfo(dailyActivity))return DataResponse.failure(401,"信息不完整！");
        dailyActivityRepository.saveAndFlush(dailyActivity);
        return DataResponse.ok();
    }

    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        dailyActivityRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<DailyActivity> listA=dailyActivityRepository.findDailyActivitiesByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    public DataResponse findAll(){
        return DataResponse.success(dailyActivityRepository.findAll());
    }
}
