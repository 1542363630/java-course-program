package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.HonorInfo;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.HonorInfoRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class HonorInfoService {
    @Autowired
    HonorInfoRepository honorInfoRepository;

    @Value("${web.upload-path}"+"HonorInfo/")
    String path;

    public boolean checkInfo(HonorInfo honorInfo){
        return DataUtil.checkInfo(honorInfo);
    }

    public DataResponse addAndUpdHonorInfo(HonorInfo honorInfo, MultipartFile[] files){
        if(!checkInfo(honorInfo))return DataResponse.failure(401,"信息不完整！");
        honorInfoRepository.saveAndFlush(honorInfo);
        return DataResponse.ok();
    }

    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        honorInfoRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<HonorInfo> listA=honorInfoRepository.findHonorInfosByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    public DataResponse findAll(){
        return DataResponse.success(honorInfoRepository.findAll());
    }
}
