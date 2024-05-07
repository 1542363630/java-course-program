package com.example.courseprogram.repository;

import com.example.courseprogram.model.DO.SelectedCourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface SelectedCourseInfoRepository extends JpaRepository<SelectedCourseInfo,Integer> {

    //根据课程查找
    List<SelectedCourseInfo> findSelectedCourseInfoByCourse_CourseId(Integer id);

    //根据课程id删除
    void deleteByCourse_CourseId(Integer id);

}
