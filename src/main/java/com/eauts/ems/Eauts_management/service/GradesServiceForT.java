package com.eauts.ems.Eauts_management.service;

import com.eauts.ems.Eauts_management.dto.GradesDTO;
import com.eauts.ems.Eauts_management.model.Grades;
import com.eauts.ems.Eauts_management.model.Schedule;
import com.eauts.ems.Eauts_management.model.Student;
import com.eauts.ems.Eauts_management.model.Teacher;
import com.eauts.ems.Eauts_management.repository.GradesRepository;
import com.eauts.ems.Eauts_management.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GradesServiceForT {

    @Autowired
    private GradesRepository gradesRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Grades> getGradesBySchedule(Long id) {
        return gradesRepository.findBySchedule_Id(id);
    }


    public Grades updateGrade(Long gradesId, float attendance, float midterm, float finalExam, Long teacherId) {
        Optional<Grades> optionalGrades = gradesRepository.findById(gradesId);
        if (optionalGrades.isPresent()) {
            Grades grades = optionalGrades.get();
            Schedule schedule = grades.getSchedule();
            Teacher assignedTeacher = schedule.getTeacher();

            if (!assignedTeacher.getTeacher_id().equals(teacherId)) {
                throw new RuntimeException("Bạn không có quyền cập nhật điểm cho lớp này!");
            }

            grades.setAttendance(attendance);
            grades.setMidterm(midterm);
            grades.setFinalExam(finalExam);
            grades.setTotal(attendance * 0.1f + midterm * 0.3f + finalExam * 0.6f);

            return gradesRepository.save(grades);
        } else {
            throw new RuntimeException("Không tìm thấy điểm số!");
        }
    }

    public void initializeGradesForSchedule(Long scheduleId) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(scheduleId);
        if (optionalSchedule.isPresent()) {
            Schedule schedule = optionalSchedule.get();
            Set<Student> students = schedule.getStudentClass().getStudents();

            for (Student student : students) {
                // Kiểm tra xem sinh viên đã có điểm chưa
                if (gradesRepository.findByScheduleAndStudent(schedule, student).isEmpty()) {
                    Grades grades = new Grades();
                    grades.setStudent(student);
                    grades.setSchedule(schedule);
                    grades.setCourse(schedule.getCourse());
                    grades.setAttendance(0.0f);
                    grades.setMidterm(0.0f);
                    grades.setFinalExam(0.0f);
                    grades.setTotal(0.0f);

                    gradesRepository.save(grades);
                }
            }
        } else {
            throw new RuntimeException("Không tìm thấy lịch học!");
        }
    }

    @Transactional
    public void updateMultipleGrades(List<GradesDTO> gradesList) {
        for (GradesDTO dto : gradesList) {
            gradesRepository.findById(dto.getGradesId()).ifPresent(grades -> {
                grades.setAttendance(dto.getAttendance());
                grades.setMidterm(dto.getMidterm());
                grades.setFinalExam(dto.getFinalExam());
                grades.setTotal(dto.getAttendance() * 0.1f + dto.getMidterm() * 0.2f + dto.getFinalExam() * 0.7f);
            });
        }
    }


}
