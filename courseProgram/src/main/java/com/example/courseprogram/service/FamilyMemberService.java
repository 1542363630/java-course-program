package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.FamilyMember;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.FamilyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyMemberService {
    @Autowired
    FamilyMemberRepository familyMemberRepository;


    //检查有没有没填的信息
    public boolean checkInfo(FamilyMember familyMember){
        if(familyMember==null){
            return false;
        }
        else if(familyMember.getStudent()==null){
            return false;
        }
        else if(familyMember.getMemberId()==null){
            return false;
        }
        else if(familyMember.getBirthday()==null){
            return false;
        }
        else if(familyMember.getGender()==null){
            return false;
        }
        else if(familyMember.getName()==null){
            return false;
        }
        else if(familyMember.getUnit()==null){
            return false;
        }
        else if(familyMember.getRelation()==null){
            return false;
        }
        return true;
    }

    //增加或者修改数据
    public DataResponse addAndUpdFamilyMember(FamilyMember familyMember){
        if(!checkInfo(familyMember))return DataResponse.failure(401,"信息不完整！");
        familyMemberRepository.saveAndFlush(familyMember);
        return DataResponse.ok();
    }

    //删除数据
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

    //获得所有数据
    public DataResponse findAll(){
        return DataResponse.success(familyMemberRepository.findAll());
    }
}
