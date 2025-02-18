package com.eauts.ems.Eauts_management.repository;

import com.eauts.ems.Eauts_management.model.Grades;
import com.eauts.ems.Eauts_management.model.Schedule;
import com.eauts.ems.Eauts_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {
    Optional<Grades> findByScheduleAndStudent(Schedule schedule, Student student);
    List<Grades> findBySchedule_Id(Long id);
}
