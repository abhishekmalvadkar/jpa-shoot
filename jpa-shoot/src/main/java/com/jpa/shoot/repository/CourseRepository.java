package com.jpa.shoot.repository;

import com.jpa.shoot.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {
}
