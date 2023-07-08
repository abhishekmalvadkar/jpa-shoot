package com.jpa.shoot.repository;

import com.jpa.shoot.dto.StudentCourseDto;
import com.jpa.shoot.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentEntity, Integer> {


    @Query(value = "SELECT" +
                   " new com.jpa.shoot.dto.StudentCourseDto(s.id,s.name,c.id,c.name,sc.enrolledDateTime)" +
                   " FROM StudentCourseEntity sc" +
                   " JOIN sc.student s" +
                   " JOIN sc.course c" +
                   " WHERE c.id = :courseId")
    List<StudentCourseDto> findEnrolledStudents(@Param("courseId") Integer courseId);

}
