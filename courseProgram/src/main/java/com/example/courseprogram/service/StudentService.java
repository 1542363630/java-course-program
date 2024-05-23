package com.example.courseprogram.service;

import com.alibaba.fastjson2.JSON;
import com.example.courseprogram.model.DO.Person;
import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DO.Teacher;
import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.model.DTO.StudentInfo;
import com.example.courseprogram.repository.*;
import com.example.courseprogram.utils.DataUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    AttendanceInfoRepository attendanceInfoRepository;
    @Autowired
    BeforeUniversityRepository beforeUniversityRepository;
    @Autowired
    DailyActivityStudentRepository dailyActivityStudentRepository;
    @Autowired
    FamilyMemberRepository familyMemberRepository;
    @Autowired
    FeeRepository feeRepository;
    @Autowired
    HomeworkRepository homeworkRepository;
    @Autowired
    HonorInfoRepository honorInfoRepository;
    @Autowired
    InnovativePracticeStudentRepository innovativePracticeStudentRepository;
    @Autowired
    LeaveInfoRepository leaveInfoRepository;
    @Autowired
    ScoreRepository scoreRepository;
    @Autowired
    SelectedCourseRepository selectedCourseRepository;
    @Autowired
    SocietyMemberRepository societyMemberRepository;

    public boolean checkInfo(Student student){
        return DataUtil.checkInfo(student);
    }

    //检查学生是否存在
    public DataResponse existStudentById(Student s){
        if(s==null||s.getStudentId()==null)return DataResponse.failure(401,"信息不完整！");
        Optional<Student> opStudent=studentRepository.findById(s.getStudentId());
        //noinspection OptionalIsPresent
        if(opStudent.isPresent()){
            return DataResponse.success(opStudent.get());
        }
        else{
            return DataResponse.failure(404,"未找到该学生！");
        }
    }

    //添加学生，初始账号和密码为学号
    public DataResponse addStudent(Student student,User user){
        if(studentRepository.existsStudentByPerson_Number(student.getPerson().getNumber())){
            return DataResponse.failure(401,"已存在");
        }
        student.setStudentId(Long.valueOf(student.getPerson().getNumber()));
        personRepository.saveAndFlush(student.getPerson());
        studentRepository.saveAndFlush(student);
        String encodedPassword = BCrypt.hashpw(String.valueOf(student.getPerson().getNumber()),BCrypt.gensalt());
        User studentUser = new User(null,"student",student.getPerson(),student.getPerson().getNumber().toString(),encodedPassword,0,null, DataUtil.getTime(),user.getUserId());
        userRepository.saveAndFlush(studentUser);
        return DataResponse.success(student);
    }

    //添加统一认证的学生，初始账号和密码为学号
    public DataResponse addSDUStudent(Student student,User user){
        if(studentRepository.existsStudentByPerson_Number(student.getPerson().getNumber())){
            return DataResponse.failure(401,"已存在");
        }
        personRepository.saveAndFlush(student.getPerson());
        studentRepository.saveAndFlush(student);
        String encodedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
        User studentUser = new User(null,"student",student.getPerson(),student.getPerson().getNumber().toString(),encodedPassword,0,null, DataUtil.getTime(),null);
        userRepository.saveAndFlush(studentUser);
        return DataResponse.success(student);
    }

    //删除学生
    public DataResponse deleteStudent(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整");
        Optional<Student> o=studentRepository.findById(id);
        if(o.isEmpty()){
            return DataResponse.failure(404,"未找到该学生");
        }
        Student student=o.get();
//        userRepository.deleteUserByPersonId(student.getPerson().getPersonId());
//        personRepository.deleteById(student.getPerson().getPersonId());
        studentRepository.deleteById(student.getStudentId());

//        attendanceInfoRepository.deleteByStudent_StudentId(student.getStudentId());
//        beforeUniversityRepository.deleteBeforeUniversityByStudent_StudentId(student.getStudentId());
//        dailyActivityStudentRepository.deleteByStudent_StudentId(student.getStudentId());
//        familyMemberRepository.deleteByStudent_StudentId(student.getStudentId());
//        feeRepository.deleteByStudent_StudentId(student.getStudentId());
//        homeworkRepository.deleteByStudent_StudentId(student.getStudentId());
//        honorInfoRepository.deleteByStudent_StudentId(student.getStudentId());
//        innovativePracticeStudentRepository.deleteByStudent_StudentId(student.getStudentId());
//        leaveInfoRepository.deleteByStudent_StudentId(student.getStudentId());
//        scoreRepository.deleteScoresByStudent_StudentId(student.getStudentId());
//        selectedCourseRepository.deleteByStudent_StudentId(student.getStudentId());
//        societyMemberRepository.deleteByStudent_StudentId(student.getStudentId());

        return DataResponse.ok();
    }

    //增加或更改学生
    public DataResponse addOrUpdateStudent(Student student){
        if(studentRepository.existsById(student.getStudentId())){
            personRepository.saveAndFlush(student.getPerson());
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

    //根据学号或姓名查询学生
    public DataResponse findByStudentIdOrName(String numName){
        if(numName==null){
            return DataResponse.failure(401,"信息不完整");
        }
        List<Student> list = studentRepository.findByStudentIdOrName(numName);
        if(list.isEmpty()){
            return DataResponse.failure(404,"未找到相关信息");
        }
        return DataResponse.success(list);
    }

    //获取所有学生
    public DataResponse getStudentList(){
        List<Student> list = studentRepository.findAll();
        return DataResponse.success(list);
    }

}
