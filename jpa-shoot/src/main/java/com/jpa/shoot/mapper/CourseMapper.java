package com.jpa.shoot.mapper;

import com.jpa.shoot.dto.request.CreateCourseRequest;
import com.jpa.shoot.dto.response.CourseResponse;
import com.jpa.shoot.entity.CourseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {
    CourseEntity toEntity(CreateCourseRequest createCourseRequest);
    CourseResponse toDto(CourseEntity courseEntity);
}
