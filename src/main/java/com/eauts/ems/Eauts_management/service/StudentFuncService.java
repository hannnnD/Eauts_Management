package com.eauts.ems.Eauts_management.service;

import com.eauts.ems.Eauts_management.dto.GradesDTO;
import com.eauts.ems.Eauts_management.dto.ScheduleDTO;
import com.eauts.ems.Eauts_management.model.Schedule;
import com.eauts.ems.Eauts_management.model.Student;
import com.eauts.ems.Eauts_management.repository.GradesRepository;
import com.eauts.ems.Eauts_management.repository.ScheduleRepository;
import com.eauts.ems.Eauts_management.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.StreamSupport.stream;

@Service
public class StudentFuncService {
    private final StudentRepository studentRepository;
    private final ScheduleRepository scheduleRepository;
    private final GradesRepository gradesRepository;

    public StudentFuncService(StudentRepository studentRepository, ScheduleRepository scheduleRepository, GradesRepository gradesRepository) {
        this.studentRepository = studentRepository;
        this.scheduleRepository = scheduleRepository;
        this.gradesRepository = gradesRepository;
    }

    public List<ScheduleDTO> getStudentSchedule(Long userId) {
        Student student = studentRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Student not found"));
        Long classId = student.getStudentClass().getClassId();

        List<Schedule> schedules = scheduleRepository.findByStudentClass_ClassId(classId);
                return schedules.stream()
                .map(schedule -> new ScheduleDTO(
                        schedule.getId(),
                        schedule.getTeacher().getFull_name(),
                        schedule.getStudentClass().getClass_name(),
                        schedule.getCourse().getCourseName(),
                        schedule.getRoom(),
                        schedule.getShift().name(),
                        schedule.getStart_date(),
                        schedule.getEnd_date()
                ))
                .collect(Collectors.toList());
    }

    public List<GradesDTO> getStudentGrades(Long userId) {
        Student student = studentRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Student not found"));
        return gradesRepository.findByStudent_StudentId(student.getStudentId())
                .stream()
                .map(GradesDTO::new)
                .collect(Collectors.toList());
    }
}