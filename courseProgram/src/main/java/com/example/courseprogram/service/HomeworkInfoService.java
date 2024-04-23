package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.HomeworkInfo;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.HomeworkInfoRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkInfoService {

    @Autowired
    HomeworkInfoRepository homeworkInfoRepository;

    //检查信息是否完整
    public boolean checkInfo(HomeworkInfo homeworkInfo){
        return DataUtil.checkInfo(homeworkInfo);
    }

    //增加或修改
    public DataResponse addOrUpdHomeworkInfo(HomeworkInfo homeworkInfo){
        if(!checkInfo(homeworkInfo)){
            return DataResponse.failure(401,"信息不完整");
        }
        if(homeworkInfo.getHomeworkInfoId()==null){
            homeworkInfoRepository.saveAndFlush(homeworkInfo);
            return DataResponse.okM("添加成功");
        }
        else {
            homeworkInfoRepository.saveAndFlush(homeworkInfo);
            return DataResponse.okM("修改成功");
        }
    }

    //删除
    public DataResponse deleteHomeworkInfo(Integer id){
        if(id==null){
            return DataResponse.error(401,"信息不完整");
        }
        else if(homeworkInfoRepository.existsById(id)){
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


}
