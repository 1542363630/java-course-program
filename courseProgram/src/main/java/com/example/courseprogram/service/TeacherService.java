package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Student;
import com.example.courseprogram.model.DO.Teacher;
import com.example.courseprogram.model.DO.User;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.PersonRepository;
import com.example.courseprogram.repository.TeacherRepository;
import com.example.courseprogram.repository.UserRepository;
import com.example.courseprogram.utils.DataUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PersonRepository personRepository;

    //增加一个教师
    public DataResponse addTeacher(Teacher teacher){
        if(teacherRepository.existsTeacherByPerson_Number(teacher.getPerson().getNumber())){
            return DataResponse.failure(401,"已存在");
        }
        teacher.setTeacherId(Long.valueOf(teacher.getPerson().getNumber()));
        personRepository.saveAndFlush(teacher.getPerson());
        teacherRepository.saveAndFlush(teacher);
        String encodedPassword = BCrypt.hashpw(teacher.getPerson().getNumber().toString(),BCrypt.gensalt());
        User teacherUser = new User(null,"teacher",teacher.getPerson(),teacher.getPerson().getNumber().toString(),encodedPassword,0,null, DataUtil.getTime(),null);
        userRepository.saveAndFlush(teacherUser);
        return DataResponse.success(teacher);
    }

    //根据id删除教师
    public DataResponse deleteTeacher(Long id){
        if(id==null)return DataResponse.failure(401,"信息不完整");
        Optional<Teacher> o=teacherRepository.findById(id);
        if(o.isEmpty()){
            return DataResponse.failure(404,"未找到该教师");
        }
        Teacher teacher=o.get();
        userRepository.deleteUserByPersonId(teacher.getPerson().getPersonId());
        personRepository.deleteById(teacher.getPerson().getPersonId());
        teacherRepository.deleteById(teacher.getTeacherId());
        return DataResponse.ok();
    }

    //增加或删除
    public DataResponse addOrUpdateTeacher(Teacher teacher){
        if(teacher==null)return DataResponse.failure(401,"信息不完整！");
        if(!Objects.equals(teacher.getTeacherId(), Long.valueOf(teacher.getPerson().getNumber()))){
            if(teacherRepository.existsTeacherByPerson_Number(teacher.getPerson().getNumber())){
                return DataResponse.failure(402,"该学工号已存在！");
            }
        }
        personRepository.saveAndFlush(teacher.getPerson());
        teacherRepository.saveAndFlush(teacher);
        return DataResponse.okM("修改成功");
    }

    //添加统一认证的教师，初始账号和密码为学号
    public DataResponse addSDUTeacher(Teacher teacher,User user){
        if(teacherRepository.existsTeacherByPerson_Number(teacher.getPerson().getNumber())){
            return DataResponse.failure(401,"已存在");
        }
        personRepository.saveAndFlush(teacher.getPerson());
        teacherRepository.saveAndFlush(teacher);
        String encodedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt());
        User teacherUser = new User(null,"teacher",teacher.getPerson(),teacher.getPerson().getNumber().toString(),encodedPassword,0,null, DataUtil.getTime(),null);
        userRepository.saveAndFlush(teacherUser);
        return DataResponse.success(teacher);
    }

    //根据用户id查找
    public DataResponse findByUserId(User user){
        Teacher teacher = teacherRepository.findByUserId(user.getUserId());
        if(teacher!=null){
            return DataResponse.success(teacher);
        }
        return DataResponse.failure(404,"未找到该学生");
    }

    //根据学号或姓名查询学生
    public DataResponse findByTeacherIdOrName(String numName){
        if(numName==null){
            return DataResponse.failure(401,"信息不完整");
        }
        List<Teacher> list = teacherRepository.findByTeacherIdOrName(numName);
        if(list.isEmpty()){
            return DataResponse.failure(404,"未找到相关信息");
        }
        return DataResponse.success(list);
    }

    //获取所有教师
    public DataResponse getTeacherList(){
        List<Teacher> list = teacherRepository.findAll();
        return DataResponse.success(list);
    }
}
