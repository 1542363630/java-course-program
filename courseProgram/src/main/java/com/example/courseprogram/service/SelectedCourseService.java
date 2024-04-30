package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.SelectedCourse;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.SelectedCourseRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectedCourseService {
    @Autowired
    SelectedCourseRepository selectedCourseRepository;

    //检查信息完整
    public boolean checkInfo(SelectedCourse selectedCourse){
        return DataUtil.checkInfo(selectedCourse);
    }

    //增加或修改数据
    public DataResponse addAndUpdSelectedCourse(SelectedCourse selectedCourse){
        if(!checkInfo(selectedCourse))return DataResponse.failure(401,"信息不完整！");
        selectedCourseRepository.saveAndFlush(selectedCourse);
        return DataResponse.ok();
    }

    //删除某学生的所有选课数据
    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        selectedCourseRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //删除某课程的所有选课数据
    public DataResponse deleteByCourseId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        selectedCourseRepository.deleteByCourse_CourseId(id);
        return DataResponse.ok();
    }

    //查找某学生的所有选课数据
    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<SelectedCourse> listA=selectedCourseRepository.findSelectedCoursesByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //查找某课程的所有选课数据
    public DataResponse findByCourseId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<SelectedCourse> listA=selectedCourseRepository.findSelectedCoursesByCourse_CourseId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        selectedCourseRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }

    //查找所有选课数据
    public DataResponse findAll(){
        return DataResponse.success(selectedCourseRepository.findAll());
    }
}
