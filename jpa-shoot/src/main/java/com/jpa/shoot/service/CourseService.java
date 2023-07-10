package com.jpa.shoot.service;

import com.jpa.shoot.dto.request.CreateCourseRequest;
import com.jpa.shoot.dto.response.CourseResponse;
import com.jpa.shoot.entity.CourseEntity;
import com.jpa.shoot.exception.RecordNotFoundException;
import com.jpa.shoot.mapper.CourseMapper;
import com.jpa.shoot.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private  final CourseMapper courseMapper;
    
    public CourseResponse saveCourse(CreateCourseRequest createCourseRequest){
        CourseEntity courseEntity = this.courseMapper.toEntity(createCourseRequest);
        CourseEntity savedEntity = this.courseRepository.save(courseEntity);
        return this.courseMapper.toDto(savedEntity);
    }
    
    public void fetchCourseById(Integer id){
        CourseEntity cousseEntityById = getCousseEntityById(id);
        this.courseMapper.toDto(cousseEntityById);

    }

    public CourseEntity getCousseEntityById(Integer id) {
        Optional<CourseEntity> optionalCourse = this.courseRepository.findById(id);
        if(optionalCourse.isPresent()){
            return optionalCourse.get();
        }
        throw new RecordNotFoundException("Cousre is not available");
    }

}
