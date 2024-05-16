package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Course;
import com.example.courseprogram.model.DO.SelectedCourseInfo;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.CourseRepository;
import com.example.courseprogram.repository.SelectedCourseInfoRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelectedCourseInfoService {

    @Autowired
    SelectedCourseInfoRepository selectedCourseInfoRepository;
    @Autowired
    CourseRepository courseRepository;

    //检查信息是否完整
    boolean checkInfo(SelectedCourseInfo selectedCourseInfo){
        return DataUtil.checkInfo(selectedCourseInfo);
    }

    //增加或更改
    public DataResponse addOrUpdSelectedCourseInfo(SelectedCourseInfo selectedCourseInfo){

        Course c=selectedCourseInfo.getCourse();
        if(c==null||c.getNumber()==null)return DataResponse.failure(401,"信息不完整");
        Optional<Course> opCourse=courseRepository.findCourseByNumber(c.getNumber());
        if(opCourse.isPresent()){
            c=opCourse.get();
            selectedCourseInfo.setCourse(c);
        }
        else{
            return DataResponse.failure(404,"未找到该课程！");
        }

        if(!checkInfo(selectedCourseInfo)){
            return DataResponse.failure(401,"信息不完整");
        }
        if(selectedCourseInfo.getSelectedCourseInfoId()==null){
            selectedCourseInfoRepository.saveAndFlush(selectedCourseInfo);
            return DataResponse.okM("添加成功");
        }
        else {
            selectedCourseInfoRepository.saveAndFlush(selectedCourseInfo);
            return DataResponse.okM("修改成功");
        }
    }

    //根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null){
            return DataResponse.failure(401,"信息不完整");
        }
        else{
            selectedCourseInfoRepository.deleteById(id);
            return DataResponse.okM("删除成功");
        }
    }

    //根据课程id删除
    public DataResponse deleteByCourseId(Integer courseId){
        if(courseId==null){
            return DataResponse.failure(401,"信息不完整");
        }
        else if(!selectedCourseInfoRepository.findSelectedCourseInfoByCourse_CourseId(courseId).isEmpty()){
            return DataResponse.okM("该课程没有任何选课信息");
        }
        else {
            selectedCourseInfoRepository.deleteByCourse_CourseId(courseId);
            return DataResponse.okM("删除成功");
        }
    }

    //查找指定 id 的信息
    public DataResponse findById(Integer id){
        if(id==null){
            return DataResponse.failure(401,"信息不完整");
        }
        else if(!selectedCourseInfoRepository.existsById(id)){
            return DataResponse.failure(404,"未找到对应信息");
        }
        else {
            Optional<SelectedCourseInfo> optionalSelectedCourseInfo = selectedCourseInfoRepository.findById(id);
            if(optionalSelectedCourseInfo.isPresent()){
                return DataResponse.success(optionalSelectedCourseInfo.get());
            }
            else {
                return DataResponse.failure(404,"未找到对应信息");
            }
        }
    }

    //查找指定课程的选课信息
    public DataResponse findByCourseId(Integer courseId){
        if (courseId==null){
            return DataResponse.failure(401,"信息不完整");
        }
        else {
            List<SelectedCourseInfo> list = selectedCourseInfoRepository.findSelectedCourseInfoByCourse_CourseId(courseId);
            if(list.isEmpty()){
                return DataResponse.failure(404,"该课程没有任何选课信息");
            }
            else {
                return DataResponse.success(list);
            }
        }
    }

    //获取所有
    public DataResponse findAll(){
        return DataResponse.success(selectedCourseInfoRepository.findAll());
    }

}
