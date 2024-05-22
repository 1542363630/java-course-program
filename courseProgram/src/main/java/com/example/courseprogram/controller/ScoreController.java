package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.Score;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.service.ScoreService;
import com.example.courseprogram.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    //增加或者修改数据
    @PostMapping("/add")
    public DataResponse addAndUpdScore(@RequestBody DataRequest dataRequest){
        return scoreService.addAndUpdScore(JsonUtil.parse(dataRequest.get("score"), Score.class));
    }

    //删除某个学生的所有成绩数据
    @PostMapping("/deleteByStudentId")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return scoreService.deleteByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //删除某个课程的所有成绩数据
    @PostMapping("/deleteByCourseId")
    public DataResponse deleteByCourseId(@RequestBody DataRequest dataRequest){
        return scoreService.deleteByCourseId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //删除某条数据
    @PostMapping("/deleteByScoreId")
    public DataResponse deleteByScoreId(@RequestBody DataRequest dataRequest){
        return scoreService.deleteByScoreId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //查找某个学生的所有数据
    @PostMapping("/findByStudentId")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return scoreService.findByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //查找某个学生的绩点
    @PostMapping("/getGradePointsByStudentId")
    public DataResponse getGradePointsByStudentId(@RequestBody DataRequest dataRequest){
        return scoreService.getGradePointsByStudentId(JsonUtil.parse(dataRequest.get("id"), Long.class));
    }

    //根据课程编号查询
    @PostMapping("/findByCourseId")
    public DataResponse findByCourseId(@RequestBody DataRequest dataRequest){
        return scoreService.findByCourseId(JsonUtil.parse(dataRequest.get("id"), Integer.class));
    }

    //根据课程编号或名称查询
    @PostMapping("/findByCourseNumberOrName")
    public DataResponse findByCourseNumberOrName(@RequestBody DataRequest dataRequest){
        return scoreService.findByCourseNumberOrName(JsonUtil.parse(dataRequest.get("numName"), String.class));
    }

    //获得所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return scoreService.findAll();
    }

}
