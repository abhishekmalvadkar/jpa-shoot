package com.jpa.shoot.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


class StudentCourseControllerTest extends AbstractIntegrationTesting {

    @Test
    @DisplayName(value = "Testing to get enrolled students in particular course")
    @Sql(scripts = {"classpath:insert_students_with_enrolled_courses.sql"})
    void testGetEnrolledStudentsApiEndpoint() throws Exception {

        String expectedResponseJsonString = """
                {
                    "data" : [
                        {
                            "studentId" : 1,
                            "studentName" : "Abhishek Malvadkar",
                            "courseId" : 1,
                            "courseName" : "Spring Boot By Abhishek Sir",
                            "courseEnrolledDateTimeStr" : "08-07-2023 12:05:24"
                        },
                         {
                            "studentId" : 2,
                            "studentName" : "Rushikesj Malvadkar",
                            "courseId" : 1,
                            "courseName" : "Spring Boot By Abhishek Sir",
                            "courseEnrolledDateTimeStr" : "08-07-2023 12:06:26"
                        },
                         {
                            "studentId" : 3,
                            "studentName" : "Sunita Malvadkar",
                            "courseId" : 1,
                            "courseName" : "Spring Boot By Abhishek Sir",
                            "courseEnrolledDateTimeStr" : "08-07-2023 12:06:52"
                        }
                    ],
                    "status" : "OK",
                    "statusCode" : 200
                }
                """;

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.
                get("/api/V1/students/courses/{courseId}", 1);

        this.mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedResponseJsonString));


    }
}