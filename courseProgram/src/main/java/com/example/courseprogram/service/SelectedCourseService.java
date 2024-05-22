package com.example.courseprogram.service;

import com.alibaba.fastjson2.JSON;
import com.example.courseprogram.model.DO.SelectedCourse;
import com.example.courseprogram.model.DO.SelectedCourseInfo;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.SelectedCourseRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectedCourseService {
    @Autowired
    SelectedCourseRepository selectedCourseRepository;
    @Autowired
    StudentService studentService;
    @Autowired
    SelectedCourseInfoService selectedCourseInfoService;

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

    //学生选课的增加和修改
    public DataResponse addByStudentAndList(Student student, List<SelectedCourseInfo> selectedCourseInfos){
        if(student==null || selectedCourseInfos==null)return DataResponse.failure(401,"信息不完整！");

        DataResponse dataResponse=studentService.existStudentById(student);
        if(dataResponse.getCode()!=200||!(dataResponse.getData() instanceof Student))return dataResponse;
        else student=(Student) dataResponse.getData();

        DataResponse res=selectedCourseInfoService.findByStudentId(student.getStudentId());
        List<SelectedCourseInfo> list;
        if(res.getCode()==200){
            list= JSON.parseArray(JSON.toJSONString(res.getData()),SelectedCourseInfo.class);
        }
        else if(res.getCode()==404){
            list=new ArrayList<SelectedCourseInfo>();
        }
        else{
            return res;
        }

        for(SelectedCourseInfo selectedCourseInfo:selectedCourseInfos){
            if(list.contains(selectedCourseInfo)){
                list.remove(selectedCourseInfo);
            }
            else{
                selectedCourseRepository.saveAndFlush(new SelectedCourse(null,student,selectedCourseInfo));
            }
        }
        for(SelectedCourseInfo selectedCourseInfo:list){
            selectedCourseRepository.deleteByStudent_StudentIdAndAndSelectedCourseInfo_SelectedCourseInfoId(student.getStudentId(),selectedCourseInfo.getSelectedCourseInfoId());
        }
        return DataResponse.okM("成功");
    }

    //删除某学生的所有选课数据
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        selectedCourseRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //删除某课程的所有选课数据
    public DataResponse deleteByCourseId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        selectedCourseRepository.deleteBySelectedCourseInfo_Course_CourseId(id);
        return DataResponse.ok();
    }

    //查找某学生的所有选课数据
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<SelectedCourse> listA=selectedCourseRepository.findSelectedCoursesByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //查找某课程的所有选课数据
    public DataResponse findByCourseId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<SelectedCourse> listA=selectedCourseRepository.findSelectedCoursesBySelectedCourseInfo_Course_CourseId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        selectedCourseRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }


    //根据学号和课程种类查询
    public DataResponse findByStudentIdAndCourseType(Long id,String type){
        if (id==null){
            return DataResponse.failure(401,"信息不完整");
        }
        else {
            List<SelectedCourseInfo> list = selectedCourseRepository.findByStudentIdAndType(id,type);
            if(list.isEmpty()){
                return DataResponse.failure(404,"未找到任何选课信息");
            }
            else {
                return DataResponse.success(list);
            }
        }
    }

    //根据学号和(课程编号或名称)查询
    public DataResponse findByStudentIdAndNumName(Long id,String numName){
        if (id==null){
            return DataResponse.failure(401,"信息不完整");
        }
        else {
            List<SelectedCourseInfo> list = selectedCourseRepository.findByStudentIdAndNumName(id,numName);
            if(list.isEmpty()){
                return DataResponse.failure(404,"该学生没有任何选课信息");
            }
            else {
                return DataResponse.success(list);
            }
        }
    }

    //根据学号和教师名称
    public DataResponse findByStudentIdAndTeacherName(Long id,String teacherName){
        if (id==null){
            return DataResponse.failure(401,"信息不完整");
        }
        else {
            List<SelectedCourseInfo> list = selectedCourseRepository.findByStudentIdAndTeacherName(id,teacherName);
            if(list.isEmpty()){
                return DataResponse.failure(404,"该学生没有任何选课信息");
            }
            else {
                return DataResponse.success(list);
            }
        }
    }


    //查找所有选课数据
    public DataResponse findAll(){
        return DataResponse.success(selectedCourseRepository.findAll());
    }
}
