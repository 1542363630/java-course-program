package com.example.courseprogram.service;

import com.example.courseprogram.model.DO.Person;
import com.example.courseprogram.model.DTO.DataResponse;
import com.example.courseprogram.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    //检查信息是否完整
    public boolean checkInfo(Person person){
        return true;
    }

    //增加person
    public DataResponse addPerson(Person person){
        Person person1 = personRepository.findByNumber(person.getNumber());
        if(person1 != null){
            return DataResponse.failure(401,"该用户已存在！");
        }
        personRepository.save(person);
        return DataResponse.ok();
    }

    //根据number删除
    public DataResponse deleteByPersonNumber(Long number) {
        Person person1 = personRepository.findByNumber(number);
        if(person1 == null){
            return DataResponse.failure(401,"该用户不存在！");
        }
        personRepository.deleteById(person1.getPersonId());
        return DataResponse.okM("删除成功");
    }

    //根据id删除
    public DataResponse deleteById(Integer id){
        if(id==null)return DataResponse.failure(401,"信息不完整！");
        personRepository.deleteById(id);
        return DataResponse.okM("删除成功");
    }

    //增加或修改
    public DataResponse addOrUpdatePerson(Person person){
        Person person1 = personRepository.findByNumber(person.getNumber());
        return DataResponse.success(personRepository.saveAndFlush(person));
    }

    //根据用户名查找
    public DataResponse findByUserName(String userName){
        return DataResponse.success(personRepository.findPersonByUserName(userName));
    }

    //获取所有信息
    public DataResponse findAll(){
        return DataResponse.success(personRepository.findAll());
    }

}
