package com.jpa.shoot.service;

import com.jpa.shoot.dto.request.CreateStudentRequest;
import com.jpa.shoot.dto.response.StudentResponse;
import com.jpa.shoot.entity.StudentEntity;
import com.jpa.shoot.exception.RecordNotFoundException;
import com.jpa.shoot.mapper.StudentMapper;
import com.jpa.shoot.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

        private final StudentRepository studentRepository;
        private  final StudentMapper studentMapper;

        public void createStudent(CreateStudentRequest createStudentRequest)
        {
                StudentEntity studentEntity = this.studentMapper.toEntity(createStudentRequest);
                this.studentRepository.save(studentEntity);

        }

        public StudentResponse fetchStudentById(Integer id){
                StudentEntity dbData = findEntityById(id);
                return this.studentMapper.toDto(dbData);

        }

        public  StudentEntity findEntityById(Integer id) {
                Optional<StudentEntity> optionalStudent = this.studentRepository.findById(id);
                if(optionalStudent.isPresent()){
                        return optionalStudent.get();
                }
                throw new RecordNotFoundException("Record Not Found");
        }


}
