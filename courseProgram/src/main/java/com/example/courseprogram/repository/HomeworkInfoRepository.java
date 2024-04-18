package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.HomeworkInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkInfoRepository extends JpaRepository<HomeworkInfo,Integer> {

    //根据课程id查找作业信息
    List<HomeworkInfo> findHomeworksInfoByCourse_CourseId(Integer courseId);

    //根据id删除
    void deleteByHomeworkInfoId(Integer id);

}
