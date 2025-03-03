package com.eauts.ems.Eauts_management.repository;

import com.eauts.ems.Eauts_management.model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses, Long> {
    boolean existsByCourseCode(String courseCode);
}
