package com.jpa.shoot.mapper;

import com.jpa.shoot.dto.request.CreateStudentRequest;
import com.jpa.shoot.dto.response.StudentResponse;
import com.jpa.shoot.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {

      StudentResponse toDto(StudentEntity studentEntity);
      StudentEntity toEntity(CreateStudentRequest createStudentRequest);
}
