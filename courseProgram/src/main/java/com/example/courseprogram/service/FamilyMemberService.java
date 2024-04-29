package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.FamilyMember;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.FamilyMemberRepository;
import com.example.courseprogram.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberService {
    @Autowired
    FamilyMemberRepository familyMemberRepository;

    //检查有没有没填的信息
    public boolean checkInfo(FamilyMember familyMember){
        return DataUtil.checkInfo(familyMember);
    }

    //增加或者修改数据
    public DataResponse addAndUpdFamilyMember(FamilyMember familyMember){
        if(!checkInfo(familyMember))return DataResponse.failure(401,"信息不完整！");
        familyMemberRepository.saveAndFlush(familyMember);
        return DataResponse.ok();
    }

    //根据学生id删除数据
    public DataResponse deleteByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        familyMemberRepository.deleteByStudent_StudentId(id);
        return DataResponse.ok();
    }

    //查找某个学生的数据
    public DataResponse findByStudentId(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<FamilyMember> b=familyMemberRepository.findFamilyMembersByStudent_StudentId(id);
        if(b==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(b);
    }

    //根据id删除数据
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        familyMemberRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }

    //获得所有数据
    public DataResponse findAll(){
        return DataResponse.success(familyMemberRepository.findAll());
    }
}
