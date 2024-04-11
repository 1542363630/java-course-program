package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
    //根据 number 查找
    Person findByNumber(String number);

    @Query(value = "select u.person from Person p,User u where u.userName = ?1")
    Person findPersonByUserName(String userName);


}
