package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.HomeworkInfo;
import com.example.courseprogram.model.DO.SelectedCourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface HomeworkInfoRepository extends JpaRepository<HomeworkInfo,Integer> {

    //根据课程id查找作业信息
    List<HomeworkInfo> findHomeworksInfoByCourse_CourseId(Integer courseId);

    //根据id删除
    void deleteByHomeworkInfoId(Integer id);

    //根据课程编号或名称查询
    @Query(value = "select h from HomeworkInfo h where h.course.number like %?1% or h.course.name like %?1% or ?1='' ")
    List<HomeworkInfo> findByCourseNumberOrName(String numName);

}
