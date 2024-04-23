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

    public DataResponse addPerson(Person person){
        Person person1 = personRepository.findByNumber(person.getNumber());
        if(person1 != null){
            return DataResponse.failure(401,"该用户已存在！");
        }
        personRepository.save(person);
        return DataResponse.ok();
    }

    public DataResponse deleteByPersonNumber(String number) {
        Person person1 = personRepository.findByNumber(number);
        if(person1 == null){
            return DataResponse.failure(401,"该用户不存在！");
        }
        personRepository.deleteById(person1.getPersonId());
        return DataResponse.ok();
    }

    public DataResponse addOrUpdatePerson(Person person){
        Person person1 = personRepository.findByNumber(person.getNumber());
        return DataResponse.success(personRepository.saveAndFlush(person));
    }

    public DataResponse findByUserName(String userName){
        return DataResponse.success(personRepository.findPersonByUserName(userName));
    }

    public DataResponse findAll(){
        return DataResponse.success(personRepository.findAll());
    }

}
