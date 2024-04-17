package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.SocietyMember;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.SocietyMemberRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocietyMemberService {
    @Autowired
    SocietyMemberRepository societyMemberRepository;

    public boolean checkInfo(SocietyMember societyMember){
        return DataUtil.checkInfo(societyMember);
    }

    public DataResponse addAndUpdSocietyMember(SocietyMember societyMember){
        if(!checkInfo(societyMember))return DataResponse.failure(401,"信息不完整！");
        societyMemberRepository.saveAndFlush(societyMember);
        return DataResponse.ok();
    }

    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        societyMemberRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<SocietyMember> listA=societyMemberRepository.findSocietyMembersByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    public DataResponse findAll(){
        return DataResponse.success(societyMemberRepository.findAll());
    }
}
