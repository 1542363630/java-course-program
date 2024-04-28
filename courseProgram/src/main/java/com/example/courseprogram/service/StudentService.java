package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Person;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.model.DTO.DataRequest;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.model.DTO.StudentInfo;
import com.example.courseprogram.repository.PersonRepository;
import com.example.courseprogram.repository.StudentRepository;
import com.example.courseprogram.repository.UserRepository;
import com.example.courseprogram.utils.DataUtil;
import com.example.courseprogram.utils.JsonUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService{

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;

    public boolean checkInfo(Student student){
        return DataUtil.checkInfo(student);
    }

    //添加学生，初始账号和密码为学号
    public DataResponse addStudent(Student student,User user){
        Person person=student.getPerson();
        if(studentRepository.existsStudentByPerson_Number(student.getPerson().getNumber())){
            return DataResponse.failure(401,"已存在");
        }
        personRepository.saveAndFlush(student.getPerson());
        studentRepository.saveAndFlush(student);
        String encodedPassword = BCrypt.hashpw(student.getPerson().getNumber(),BCrypt.gensalt());
        User studentUser = new User(null,"student",student.getPerson(),student.getPerson().getNumber(),encodedPassword,0,null, DataUtil.getTime(),user.getUserId());
        userRepository.saveAndFlush(studentUser);
        return DataResponse.ok();
    }

    //删除学生
    public DataResponse deleteStudent(Student student){
        if(!studentRepository.existsById(student.getStudentId())){
            return DataResponse.failure(404,"未找到该学生");
        }
        studentRepository.deleteById(student.getStudentId());
        userRepository.deleteUserByPersonPersonId(student.getPerson().getPersonId());
        personRepository.deleteById(student.getPerson().getPersonId());
        return DataResponse.ok();
    }

    //增加或更改学生
    public DataResponse addOrUpdateStudent(Student student){
        if(studentRepository.existsById(student.getStudentId())){
            return DataResponse.success(studentRepository.saveAndFlush(student));
        }
        else {
            return DataResponse.success(studentRepository.updateStudentByStudentId(student,student.getStudentId()));
        }
    }

    //根据uid查找学生
    public DataResponse findByUserId(User user){
        Student student = studentRepository.findByUserId(user.getUserId());
        if(student!=null){
            return DataResponse.success(student);
        }
        return DataResponse.failure(404,"未找到该学生");
    }

    //获取所有学生
    public DataResponse getStudentList(){
        List<Student> list = studentRepository.findAll();
        return DataResponse.success(list);
    }

}
