package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.DailyActivity;
import com.example.courseprogram.model.DO.DailyActivityStudent;
import com.example.courseprogram.model.DO.InnovativePractice;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.DailyActivityRepository;
import com.example.courseprogram.repository.DailyActivityStudentRepository;
import com.example.courseprogram.repository.StudentRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DailyActivityService {
    @Autowired
    DailyActivityRepository dailyActivityRepository;
    @Autowired
    DailyActivityStudentRepository dailyActivityStudentRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentService studentService;

    //检查信息完整
    public boolean checkInfo(DailyActivity dailyActivity){
        return DataUtil.checkInfo(dailyActivity);
    }

    //检查活动是否存在
    public DataResponse existDailyActivityById(DailyActivity dailyActivity){
        if(dailyActivity==null||dailyActivity.getActivityId()==null)return DataResponse.failure(401,"信息不完整！");
        Optional<DailyActivity> opDailyActivity=dailyActivityRepository.findById(dailyActivity.getActivityId());
        //noinspection OptionalIsPresent
        if(opDailyActivity.isPresent()){
            return DataResponse.success(opDailyActivity.get());
        }
        else{
            return DataResponse.failure(404,"未找到该活动！");
        }
    }

    //增加或更改
    public DataResponse addAndUpdDailyActivity(DailyActivity dailyActivity, List<Student> students){
        if(!checkInfo(dailyActivity))return DataResponse.failure(401,"信息不完整！");
        dailyActivityRepository.saveAndFlush(dailyActivity);
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
            else if(dailyActivityStudentRepository.selectByIdAndStudentId(dailyActivity.getActivityId(),student.getStudentId())!=0){
                msg=msg+"该学生已存在";
            }
            else{
                student=(Student) dataResponse.getData();
                DailyActivityStudent dailyActivityStudent=new DailyActivityStudent(null,student,dailyActivity);
//                students.set(index,);
                dailyActivityStudentRepository.saveAndFlush(dailyActivityStudent);
                msg=msg+"添加成功";
            }
        }
        return DataResponse.okM(msg);
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
    public DataResponse findByStudentIdAndType(Long id,String type){
        if(id==null||type==null)return DataResponse.failure(401,"信息不完整");
        List<DailyActivity> list=dailyActivityRepository.findByStudentIdAndType(id,type);
        if(list==null)return DataResponse.failure(404,"未找到相关信息");
        return DataResponse.success(list);
    }

    //根据名称查找
    public DataResponse findByName(String name){
        if(name==null)return DataResponse.failure(401,"信息不完整");
        List<DailyActivity> list=dailyActivityRepository.findByName(name);
        if(list==null)return DataResponse.failure(404,"未找到相关信息");
        return DataResponse.success(list);
    }

    //获取所有课程
    public DataResponse findAll(){
        return DataResponse.success(dailyActivityRepository.findAll());
    }
}
