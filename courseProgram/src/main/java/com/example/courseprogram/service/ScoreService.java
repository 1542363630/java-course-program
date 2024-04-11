package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Score;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.ScoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    ScoreRepository scoreRepository;

    public boolean checkInfo(Score score){
        if(score==null){
            return false;
        }
        else if(score.getStudent()==null){
            return false;
        }
        else if(score.getMark()==null){
            return false;
        }
        else if(score.getCourse()==null){
            return false;
        }
        else if(score.getRanking()==null){
            return false;
        }
        return true;
    }

    //增加或者修改数据
    public DataResponse addAndUpdFee(Score score){
        if(!checkInfo(score))return DataResponse.failure(401,"信息不完整！");
        scoreRepository.saveAndFlush(score);
        return DataResponse.ok();
    }

    //删除某个学生的所有成绩数据
    public DataResponse deleteByStudentId(Integer id){
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
    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<Score> b=scoreRepository.findScoresByStudent_StudentId(id);
        if(b==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(b);
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
