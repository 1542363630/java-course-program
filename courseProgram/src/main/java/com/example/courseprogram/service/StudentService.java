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
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;

    //添加学生，初始账号和密码为学号
    public DataResponse addStudent(DataRequest dataRequest){
        StudentInfo studentInfo= JsonUtil.prase(dataRequest.get("studentInfo"), StudentInfo.class);
        User user=JsonUtil.prase(dataRequest.get("user"), User.class);
        if(studentRepository.existsStudentByPerson_Number(studentInfo.getNumber())){
            return DataResponse.failure(401,"已存在");
        }
        Student student=new Student(studentInfo);
        studentRepository.saveAndFlush(student);
        personRepository.saveAndFlush((Person) studentInfo);
        String encodedPassword = BCrypt.hashpw(studentInfo.getNumber(),BCrypt.gensalt());
        User studentUser = new User(null,"student",studentInfo,studentInfo.getNumber(),encodedPassword,0,null, DataUtil.getTime(),user.getUserId());
        userRepository.saveAndFlush(studentUser);
        return DataResponse.ok();
    }

    public DataResponse deleteStudent(Student student){
        if(!studentRepository.existsById(student.getStudentId())){
            return DataResponse.failure(404,"未找到该学生");
        }
        studentRepository.deleteById(student.getStudentId());
        userRepository.deleteUserByPersonPersonId(student.getPerson().getPersonId());
        personRepository.deleteById(student.getPerson().getPersonId());
        return DataResponse.ok();
    }

    public DataResponse addOrUpdateStudent(Student student){
        if(studentRepository.existsById(student.getStudentId())){
            return DataResponse.success(studentRepository.saveAndFlush(student));
        }
        else {
            return DataResponse.success(studentRepository.updateStudentByStudentId(student,student.getStudentId()));
        }
    }

    public DataResponse findByUserId(User user){
        Student student = studentRepository.findByUserId(user.getUserId());
        if(student!=null){
            return DataResponse.success(student);
        }
        return DataResponse.failure(404,"未找到该学生");
    }

    public DataResponse getStudentList(){
        List<Student> list = studentRepository.findAll();
        return DataResponse.success(list);
    }

    public DataResponse findAll(){
        return DataResponse.success(studentRepository.findAll());
    }
}
