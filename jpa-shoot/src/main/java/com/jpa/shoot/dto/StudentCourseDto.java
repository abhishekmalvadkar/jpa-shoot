package com.jpa.shoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@RequiredArgsConstructor
public final class StudentCourseDto {
    private final Integer studentId;
    private final String studentName;
    private final Integer courseId;
    private final String courseName;

    @JsonIgnore
    private final LocalDateTime courseEnrolledDateTime;

    public String getCourseEnrolledDateTimeStr() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");
        return dateTimeFormatter.format(courseEnrolledDateTime);
    }
}