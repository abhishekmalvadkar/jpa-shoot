package com.jpa.shoot.controller;

import com.jpa.shoot.dto.StudentCourseDto;
import com.jpa.shoot.dto.response.ApiResponse;
import com.jpa.shoot.service.StudentCourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/V1/students/courses")
@Slf4j
@RequiredArgsConstructor
public class StudentCourseController {

    private final StudentCourseService studentCourseService;

    /**
     * We will follow below naming convention for methods
     * "get*" in controller (Ex. getEnrolledStudents())
     * "fetch*" in service (Ex. fetchEnrolledStudents())
     * "find*" in repository (Ex. findEnrolledStudents())
     */
   @GetMapping("{courseId}")
    public ApiResponse<List<StudentCourseDto>> getEnrolledStudents(@PathVariable("courseId") Integer courseId) {
       List<StudentCourseDto> enrolledStudents = this.studentCourseService.fetchEnrolledStudents(courseId);
       ApiResponse<List<StudentCourseDto>> apiResponse = new ApiResponse<>();
       apiResponse.setData(enrolledStudents);
       apiResponse.setStatus(HttpStatus.OK);
       return apiResponse;
   }

}
