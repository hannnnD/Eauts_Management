package com.eauts.ems.Eauts_management.repository;

import com.eauts.ems.Eauts_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT MAX(CAST(SUBSTRING(s.user.username, 5, 5) AS int)) FROM Student s WHERE s.enrollment_year = :year")
    Integer findMaxStudentNumberByYear(@Param("year") int year);
}
