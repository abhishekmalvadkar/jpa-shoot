package com.jpa.shoot.service;

import com.jpa.shoot.dto.StudentCourseDto;
import com.jpa.shoot.repository.StudentCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepository;


    public List<StudentCourseDto> fetchEnrolledStudents(Integer courseId) {
        log.trace("<<<<<< fetchEnrolledStudents()");
        log.trace("fetchEnrolledStudents() >>>>>>");
        return this.studentCourseRepository.findEnrolledStudents(courseId);
    }


}
