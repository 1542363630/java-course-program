package com.example.courseprogram.service;

import com.alibaba.fastjson2.JSON;
import com.example.courseprogram.model.DO.Course;
import com.example.courseprogram.model.DO.HomeworkInfo;
import com.example.courseprogram.model.DO.SelectedCourseInfo;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.CourseRepository;
import com.example.courseprogram.repository.HomeworkInfoRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class HomeworkInfoService {

    @Autowired
    HomeworkInfoRepository homeworkInfoRepository;
    @Autowired
    CourseRepository courseRepository;

    @Value("${web.upload-path}"+"HomeworkInfo")
    String path;

    //检查信息是否完整
    public boolean checkInfo(HomeworkInfo homeworkInfo){
        return DataUtil.checkInfo(homeworkInfo);
    }

    //增加或修改
    public DataResponse addOrUpdHomeworkInfo(HomeworkInfo homeworkInfo){

        Course c=homeworkInfo.getCourse();
        if(c==null||c.getNumber()==null)return DataResponse.failure(401,"信息不完整");
        Optional<Course> opCourse=courseRepository.findCourseByNumber(c.getNumber());
        if(opCourse.isPresent()){
            c=opCourse.get();
            homeworkInfo.setCourse(c);
        }
        else{
            return DataResponse.failure(404,"未找到该课程！");
        }

        if(!checkInfo(homeworkInfo)){
            return DataResponse.failure(401,"信息不完整");
        }
        homeworkInfoRepository.saveAndFlush(homeworkInfo);
        return DataResponse.okM("添加成功");
    }

    //根据id删除一条
    public DataResponse deleteById(Integer id){
        if(id==null){
            return DataResponse.error(401,"信息不完整");
        }
        else if(!homeworkInfoRepository.existsById(id)){
            return DataResponse.error(404,"未找到这条作业信息");
        }
        else {
            homeworkInfoRepository.deleteByHomeworkInfoId(id);
            return DataResponse.okM("删除成功");
        }
    }

    //根据课程id查找
    public DataResponse findByCourseId(Integer id){
        List<HomeworkInfo> list=homeworkInfoRepository.findHomeworksInfoByCourse_CourseId(id);
        if(list.isEmpty())return DataResponse.error(404,"未找到课程对应的作业信息");
        else return DataResponse.success(list);
    }

    //根据课程编号或名称查询
    public DataResponse findByCourseNumberOrName(String numName){
        if (numName==null){
            return DataResponse.failure(401,"信息不完整");
        }
        else {
            List<HomeworkInfo> list = homeworkInfoRepository.findByCourseNumberOrName(numName);
            if(list.isEmpty()){
                return DataResponse.failure(404,"未找到相关信息");
            }
            else {
                return DataResponse.success(list);
            }
        }
    }

    //获取所有信息
    public DataResponse findAll(){
        return DataResponse.success(homeworkInfoRepository.findAll());
    }
}
