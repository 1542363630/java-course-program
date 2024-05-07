package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    //根据 PersonId 查找
    User findUserByPersonPersonId(Integer personId);

    //根据 userName 查找
    User findUserByUserName(String userName);

    //根据 userType 查找
    List<User> findUserByUserType(String userType);

    @Modifying
    @Query(value = "delete from User u where u.person.personId=?1")
    void deleteUserByPersonId(Integer personId);

    @Query(value = "select u.userType from User u where u.userName=?1")
    String findUserTypeByName(String UserName);
}
