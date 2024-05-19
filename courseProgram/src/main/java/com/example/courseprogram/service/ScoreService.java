package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Course;
import com.example.courseprogram.model.DO.Score;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.CourseRepository;
import com.example.courseprogram.repository.ScoreRepository;
import com.example.courseprogram.repository.StudentRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {
    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    //检查信息完整
    public boolean checkInfo(Score score){
        return DataUtil.checkInfo(score);
    }

    //增加或者修改数据
    public DataResponse addAndUpdScore(Score score){
        Student s=score.getStudent();
        if(s==null||s.getStudentId()==null)return DataResponse.failure(401,"信息不完整！");
        Optional<Student> opStudent=studentRepository.findById(s.getStudentId());
        if(opStudent.isPresent()){
            s=opStudent.get();
            score.setStudent(s);
        }
        else{
            return DataResponse.failure(404,"未找到该学生！");
        }

        Course c=score.getCourse();
        if(c==null||c.getNumber()==null)return DataResponse.failure(401,"信息不完整");
        Optional<Course> opCourse=courseRepository.findCourseByNumber(c.getNumber());
        if(opCourse.isPresent()){
            c=opCourse.get();
            score.setCourse(c);
        }
        else{
            return DataResponse.failure(404,"未找到该课程！");
        }

        if(!checkInfo(score))return DataResponse.failure(401,"信息不完整！");
        scoreRepository.saveAndFlush(score);
        return DataResponse.ok();
    }

    //删除某个学生的所有成绩数据
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        scoreRepository.deleteScoresByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //删除某个课程的所有成绩数据
    public DataResponse deleteByCourseId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        scoreRepository.deleteScoresByCourse_CourseId(id);
        return DataResponse.ok();
    }

    //删除某条数据
    public DataResponse deleteByScoreId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        scoreRepository.deleteById(id);
        return DataResponse.ok();
    }

    //查找某个学生的所有数据
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<Score> b=scoreRepository.findScoresByStudent_StudentId(id);
        if(b==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(b);
    }

    //获取某个学生的绩点
    public DataResponse getGradePointsByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整");
        List<Score> list=scoreRepository.findScoresByStudent_StudentId(id);
        if(list.isEmpty())return DataResponse.failure(404,"未找到相关信息");
        Double gradePoints = 0.0;
        Double totalPoints = 0.0;
        for(Score score:list){
            if(score.getIsCal()){
                totalPoints+=score.getCourse().getCredit();
                gradePoints+=score.getCourse().getCredit()*score.getMark();
            }
        }
        gradePoints/=totalPoints;
        return DataResponse.success(gradePoints);
    }

    //查找某个课程的所有数据
    public DataResponse findByCourseId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<Score> b=scoreRepository.findScoresByCourse_CourseId(id);
        if(b==null)return DataResponse.failure(404,"未找到该课程的信息");
        return DataResponse.success(b);
    }

    //获得所有数据
    public DataResponse findAll(){
        return DataResponse.success(scoreRepository.findAll());
    }

}
