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

    //检查信息完整
    public boolean checkInfo(SocietyMember societyMember){
        return DataUtil.checkInfo(societyMember);
    }

    //增加或修改
    public DataResponse addAndUpdSocietyMember(SocietyMember societyMember){
        if(!checkInfo(societyMember))return DataResponse.failure(401,"信息不完整！");
        societyMemberRepository.saveAndFlush(societyMember);
        return DataResponse.ok();
    }

    //根据学生id删除
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        societyMemberRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //根据学生id查找
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<SocietyMember> listA=societyMemberRepository.findSocietyMembersByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

    //根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        societyMemberRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }

    //获取所有信息
    public DataResponse findAll(){
        return DataResponse.success(societyMemberRepository.findAll());
    }
}
