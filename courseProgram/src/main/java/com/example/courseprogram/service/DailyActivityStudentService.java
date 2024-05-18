package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.DailyActivity;
import com.example.courseprogram.model.DO.DailyActivityStudent;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.DailyActivityStudentRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyActivityStudentService {
    @Autowired
    DailyActivityStudentRepository dailyActivityStudentRepository;
    @Autowired
    DailyActivityService dailyActivityService;
    @Autowired
    StudentService studentService;

    //检查信息完整
    public boolean checkInfo(DailyActivityStudent dailyActivityStudent){
        return DataUtil.checkInfo(dailyActivityStudent);
    }

    //增加或更改
    public DataResponse addAndUpdDailyActivityStudent(DailyActivityStudent dailyActivityStudent){
        DataResponse dataResponse=dailyActivityService.existDailyActivityById(dailyActivityStudent.getDailyActivity());
        if(dataResponse.getCode()!=200||!(dataResponse.getData() instanceof DailyActivity))return dataResponse;
        else dailyActivityStudent.setDailyActivity((DailyActivity) dataResponse.getData());

        dataResponse=studentService.existStudentById(dailyActivityStudent.getStudent());
        if(dataResponse.getCode()!=200||!(dataResponse.getData() instanceof Student))return dataResponse;
        else dailyActivityStudent.setStudent((Student) dataResponse.getData());
        
        if(!checkInfo(dailyActivityStudent))return DataResponse.failure(401,"信息不完整！");
        dailyActivityStudentRepository.saveAndFlush(dailyActivityStudent);
        return DataResponse.ok();
    }

    //根据活动id和学生数组删除
    public DataResponse deleteByIdAndStudents(Integer id,List<Student> students){
        if(id==null||students==null)return DataResponse.failure(401,"信息不完整！");
        String msg="";
        for(Student student:students){
            int index=students.indexOf(student);
            DataResponse dataResponse=studentService.existStudentById(student);
            msg=msg+"\n"+"第"+index+"个：";
            if(dataResponse.getCode()!=200){
                msg=msg+dataResponse.getMessage();
            }
            else if(!(dataResponse.getData() instanceof Student)){
                msg=msg+"不是学生实例";
            }
            else {
                student=(Student) dataResponse.getData();
                dailyActivityStudentRepository.deleteByIdAndStudents(id,student.getStudentId());
                msg=msg+"删除成功";
            }
        }
        return DataResponse.okM(msg);
    }

    //根据学生id删除
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        dailyActivityStudentRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //根据学生id查找
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<DailyActivityStudent> listA=dailyActivityStudentRepository.findDailyActivityStudentsByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //根据活动id删除
    public DataResponse deleteByDailyActivityId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        dailyActivityStudentRepository.deleteByDailyActivity_ActivityId(id);
        return DataResponse.ok();
    }

    //根据活动id查找
    public DataResponse findByDailyActivityId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<DailyActivityStudent> listA=dailyActivityStudentRepository.findDailyActivityStudentsByDailyActivity_ActivityId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该活动的参与人员信息");
        return DataResponse.success(listA);
    }

    //根据id删除数据
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        dailyActivityStudentRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }

//    //根据学号和活动类型查询
//    public DataResponse findByStudentIdAndType(Long id,String type){
//        if(id==null||type==null)return DataResponse.failure(401,"信息不完整");
//        List<DailyActivityStudent> list=dailyActivityStudentRepository.findByStudentIdAndType(id,type);
//        if(list==null)return DataResponse.failure(404,"未找到相关信息");
//        return DataResponse.success(list);
//    }

    //获取所有活动参与信息
    public DataResponse findAll(){
        return DataResponse.success(dailyActivityStudentRepository.findAll());
    }
    
}
