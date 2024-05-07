package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Teacher;
import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.PersonRepository;
import com.example.courseprogram.repository.TeacherRepository;
import com.example.courseprogram.repository.UserRepository;
import com.example.courseprogram.utils.DataUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    TeacherRepository teacherRepository;

    UserRepository userRepository;

    PersonRepository personRepository;

    //增加一个教师
    public DataResponse addTeacher(Teacher teacher){
        if(teacherRepository.existsTeacherByPerson_Number(teacher.getPerson().getNumber())){
            return DataResponse.failure(401,"已存在");
        }
        teacherRepository.saveAndFlush(teacher);
        personRepository.saveAndFlush(teacher.getPerson());
        String encodedPassword = BCrypt.hashpw(teacher.getPerson().getNumber().toString(),BCrypt.gensalt());
        User teacherUser = new User(null,"teacher",teacher.getPerson(),teacher.getPerson().getNumber().toString(),encodedPassword,0,null, DataUtil.getTime(),null);
        userRepository.saveAndFlush(teacherUser);
        return DataResponse.ok();
    }

    //根据id删除教师
    public DataResponse deleteTeacher(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整");
        Optional<Teacher> o=teacherRepository.findById(id);
        if(o.isEmpty()){
            return DataResponse.failure(404,"未找到该教师");
        }
        Teacher teacher=o.get();
        teacherRepository.deleteById(teacher.getTeacherId());
        userRepository.deleteUserByPersonPersonId(teacher.getPerson().getPersonId());
        personRepository.deleteById(teacher.getPerson().getPersonId());
        return DataResponse.ok();
    }

    //增加或删除
    public DataResponse addOrUpdateTeacher(Teacher teacher){
        if(teacherRepository.existsById(teacher.getTeacherId())){
            return DataResponse.success(teacherRepository.saveAndFlush(teacher));
        }
        else {
            return DataResponse.success(teacherRepository.updateTeacherByTeacherId(teacher));
        }
    }

    //根据用户id查找
    public DataResponse findByUserId(User user){
        Teacher teacher = teacherRepository.findByUserId(user.getUserId());
        if(teacher!=null){
            return DataResponse.success(teacher);
        }
        return DataResponse.failure(404,"未找到该学生");
    }

    //获取所有教师
    public DataResponse getTeacherList(){
        List<Teacher> list = teacherRepository.findAll();
        return DataResponse.success(list);
    }
}
