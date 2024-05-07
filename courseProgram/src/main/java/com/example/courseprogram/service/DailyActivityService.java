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

    //检查信息完整
    public boolean checkInfo(DailyActivity dailyActivity){
        return DataUtil.checkInfo(dailyActivity);
    }

    //增加或更改
    public DataResponse addAndUpdDailyActivity(DailyActivity dailyActivity){
        if(!checkInfo(dailyActivity))return DataResponse.failure(401,"信息不完整！");
        dailyActivityRepository.saveAndFlush(dailyActivity);
        return DataResponse.ok();
    }

    //根据学生id删除
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        dailyActivityRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //根据学生id查找
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<DailyActivity> listA=dailyActivityRepository.findDailyActivitiesByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //根据id删除数据
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        dailyActivityRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }

    //根据学号和活动类型查询
    public DataResponse findByStudentIdAndType(Integer id,String type){
        if(id==null||type==null)return DataResponse.failure(401,"信息不完整");
        List<DailyActivity> list=dailyActivityRepository.findByStudentIdAndType(id,type);
        if(list==null)return DataResponse.failure(404,"未找到相关信息");
        return DataResponse.success(list);
    }

    //获取所有课程
    public DataResponse findAll(){
        return DataResponse.success(dailyActivityRepository.findAll());
    }
}
