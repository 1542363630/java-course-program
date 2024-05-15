package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.HonorInfo;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.HonorInfoRepository;
import com.example.courseprogram.repository.StudentRepository;
import com.example.courseprogram.utils.DataUtil;
import com.example.courseprogram.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class HonorInfoService {
    @Autowired
    HonorInfoRepository honorInfoRepository;

    @Autowired
    StudentRepository studentRepository;

    @Value("${web.upload-path}"+"HonorInfo/")
    String path;

//    检查信息完整
    public boolean checkInfo(HonorInfo honorInfo){
        return DataUtil.checkInfo(honorInfo);
    }

//    增加或修改
    public DataResponse addAndUpdHonorInfo(HonorInfo honorInfo, MultipartFile[] files){
        Student s=honorInfo.getStudent();
        if(s==null||s.getStudentId()==null)return DataResponse.failure(401,"信息不完整！");
        Optional<Student> opStudent=studentRepository.findById(s.getStudentId());
        if(opStudent.isPresent()){
            s=opStudent.get();
            honorInfo.setStudent(s);
        }
        else{
            return DataResponse.failure(404,"未找到该学生！");
        }
        if(!checkInfo(honorInfo))return DataResponse.failure(401,"信息不完整！");
        honorInfoRepository.saveAndFlush(honorInfo);
        for (MultipartFile file : files) {
            String message = FileUtil.upload(file, path, file.getOriginalFilename());
            if (message.equals("生成父目录失败") || message.equals("报错了")) {
                return DataResponse.failure(402, message);
            }
        }

        return DataResponse.okM("成功！");
    }

//    根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        honorInfoRepository.deleteById(id);
        return DataResponse.okM("删除成功！");
    }

//    根据学生id删除
    public DataResponse deleteByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        honorInfoRepository.deleteByStudent_StudentId(id);
        return DataResponse.okM("删除成功！");
    }

//    根据学生id查找
    public DataResponse findByStudentId(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        List<HonorInfo> listA=honorInfoRepository.findHonorInfosByStudent_StudentId(id);
        if(listA==null)return DataResponse.failure(404,"未找到该同学的信息");
        return DataResponse.success(listA);
    }

//    获取所有信息
    public DataResponse findAll(){
        return DataResponse.success(honorInfoRepository.findAll());
    }
}
