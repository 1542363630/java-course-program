package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.AttendanceInfo;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.AttendanceInfoRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceInfoService {

    @Autowired
    AttendanceInfoRepository attendanceInfoRepository;

    //检查信息是否完整
    public boolean checkInfo(AttendanceInfo attendanceInfo){
        return DataUtil.checkInfo(attendanceInfo);
    }

    //增加或者修改数据
    public DataResponse addAndUpdAttendanceInfo(AttendanceInfo attendanceInfo){
        if(!checkInfo(attendanceInfo))return DataResponse.failure(401,"信息不完整！");
        attendanceInfoRepository.saveAndFlush(attendanceInfo);
        return DataResponse.ok();
    }

    //删除指定id的信息
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整");
        attendanceInfoRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }

    //删除某学生的所有考勤信息
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        attendanceInfoRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //查找某学生的考勤信息
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<AttendanceInfo> listA=attendanceInfoRepository.findAttendanceInfosByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //根据是否考勤查找
    public DataResponse findByIsAttended(String isAttended){
        if(isAttended==null)return DataResponse.failure(401,"信息不完整");
        List<AttendanceInfo> list=attendanceInfoRepository.findAttendanceInfosByIsAttended(isAttended);
        if(list==null)return DataResponse.failure(404,"未找到相关信息");
        return DataResponse.success(list);
    }

//    //查找某课程的考勤信息
//    public DataResponse findByCourseId(Integer id){
//        if(id==null)return DataResponse.failure(401,"信息不完整！");
//        List<AttendanceInfo> listA=attendanceInfoRepository.findAttendanceInfosByCourse_CourseId(id);
//        if(listA==null)return DataResponse.failure(404,"未找到该课程的信息");
//        return DataResponse.success(listA);
//    }

    //获取所有数据
    public DataResponse findAll(){
        return DataResponse.success(attendanceInfoRepository.findAll());
    }

}
