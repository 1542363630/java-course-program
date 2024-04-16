package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Course;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public boolean checkInfo(Course course){
        if(course==null){
            return false;
        }
        else if(course.getCoursePath()==null){
            return false;
        }
        else if(course.getNumber()==null){
            return false;
        }
        else if(course.getCredit()==null){
            return false;
        }
        else if(course.getName()==null){
            return false;
        }
        return true;
    }

    //增加或者修改数据
    public DataResponse addAndUpdCourse(Course course){
        if(!checkInfo(course))return DataResponse.failure(401,"信息不完整！");
        courseRepository.saveAndFlush(course);
        return DataResponse.ok();
    }

    //删除数据
    public DataResponse deleteByCourseId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        courseRepository.deleteCourseByCourseId(id);
        return DataResponse.ok();
    }

    //根据课程编号查找课程的数据
    public DataResponse findByCourseNumber(String number){
        if(number==null)return DataResponse.failure(401,"信息不完整！");
        List<Course> list=courseRepository.findCourseListByNum(number);
        if(list==null)return DataResponse.failure(404,"未找到相关的信息");
        return DataResponse.success(list);
    }

    //根据课程名字查找课程的数据
    public DataResponse findByCourseName(String name){
        if(name==null)return DataResponse.failure(401,"信息不完整！");
        List<Course> list=courseRepository.findCourseListByName(name);
        if(list==null)return DataResponse.failure(404,"未找到相关的信息");
        return DataResponse.success(list);
    }

    //获得所有数据
    public DataResponse findAll(){
        return DataResponse.success(courseRepository.findAll());
    }

}
