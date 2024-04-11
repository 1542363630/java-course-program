package com.example.courseprogram.controller;

import com.example.courseprogram.model.DO.Fee;
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
    @PostMapping("/addAndUpdScore")
    public DataResponse addAndUpdScore(@RequestBody DataRequest dataRequest){
        return scoreService.addAndUpdFee(JsonUtil.prase(dataRequest.get("score"), Score.class));
    }

    //删除某个学生的所有成绩数据
    @PostMapping("/deleteByStudentId")
    public DataResponse deleteByStudentId(@RequestBody DataRequest dataRequest){
        return scoreService.deleteByStudentId(JsonUtil.prase(dataRequest.get("id"), Integer.class));
    }

    //删除某个课程的所有成绩数据
    @PostMapping("/deleteByCourseId")
    public DataResponse deleteByCourseId(@RequestBody DataRequest dataRequest){
        return scoreService.deleteByCourseId(JsonUtil.prase(dataRequest.get("id"), Integer.class));
    }

    //删除某条数据
    @PostMapping("/deleteByScoreId")
    public DataResponse deleteByScoreId(@RequestBody DataRequest dataRequest){
        return scoreService.deleteByScoreId(JsonUtil.prase(dataRequest.get("id"), Integer.class));
    }

    //查找某个学生的所有数据
    @PostMapping("/findByStudentId")
    public DataResponse findByStudentId(@RequestBody DataRequest dataRequest){
        return scoreService.findByStudentId(JsonUtil.prase(dataRequest.get("id"), Integer.class));
    }

    //查找某个课程的所有数据
    @PostMapping("/findByCourseId")
    public DataResponse findByCourseId(@RequestBody DataRequest dataRequest){
        return scoreService.findByCourseId(JsonUtil.prase(dataRequest.get("id"), Integer.class));
    }

    //获得所有数据
    @PostMapping("/findAll")
    public DataResponse findAll(){
        return scoreService.findAll();
    }

}
