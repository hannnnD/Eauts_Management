package com.eauts.ems.Eauts_management.repository;

import com.eauts.ems.Eauts_management.model.Grades;
import com.eauts.ems.Eauts_management.model.Schedule;
import com.eauts.ems.Eauts_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GradesRepository extends JpaRepository<Grades, Long> {
    Optional<Grades> findByScheduleAndStudent(Schedule schedule, Student student);
    List<Grades> findBySchedule_Id(Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Grades g SET g.attendance = :attendance, g.midterm = :midterm, g.finalExam = :finalExam, g.total = :total WHERE g.id = :gradesId")
    void updateGrade(@Param("gradesId") Long gradesId,
                     @Param("attendance") float attendance,
                     @Param("midterm") float midterm,
                     @Param("finalExam") float finalExam,
                     @Param("total") float total);
    @Modifying
    @Transactional
    @Query("UPDATE Grades g SET g.attendance = :attendance, g.midterm = :midterm, g.finalExam = :finalExam, g.total = :total WHERE g.id = :gradesId")
    void updateGradesBatch(@Param("gradesId") List<Long> gradesIds,
                           @Param("attendance") List<Float> attendance,
                           @Param("midterm") List<Float> midterm,
                           @Param("finalExam") List<Float> finalExam,
                           @Param("total") List<Float> total);

}
