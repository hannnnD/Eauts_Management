package com.eauts.ems.Eauts_management.service;

import com.eauts.ems.Eauts_management.model.ClassEntity;
import com.eauts.ems.Eauts_management.model.Courses;
import com.eauts.ems.Eauts_management.model.Schedule;
import com.eauts.ems.Eauts_management.model.Teacher;
import com.eauts.ems.Eauts_management.repository.ClassRepository;
import com.eauts.ems.Eauts_management.repository.CourseRepository;
import com.eauts.ems.Eauts_management.repository.ScheduleRepository;
import com.eauts.ems.Eauts_management.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Schedule saveSchedule(Schedule schedule) {
        if (schedule.getTeacher() == null || schedule.getTeacher().getTeacher_id() == null) {
            throw new IllegalArgumentException("Teacher ID must not be null");
        }
        if (schedule.getStudentClass() == null || schedule.getStudentClass().getClass_id() == null) {
            throw new IllegalArgumentException("Class ID must not be null");
        }
        if (schedule.getCourse() == null || schedule.getCourse().getCourses_id() == null) {
            throw new IllegalArgumentException("Course ID must not be null");
        }

        // Tìm dữ liệu từ database
        Teacher teacher = teacherRepository.findById(schedule.getTeacher().getTeacher_id())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        ClassEntity studentClass = classRepository.findById(schedule.getStudentClass().getClass_id())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        Courses courses = courseRepository.findById(schedule.getCourse().getCourses_id())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Ánh xạ lại dữ liệu
        schedule.setTeacher(teacher);
        schedule.setStudentClass(studentClass);
        schedule.setCourse(courses);

        return scheduleRepository.save(schedule);
    }
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }

    public Schedule updateSchedule(Long id, Schedule updatedSchedule) {
        Optional<Schedule> existingScheduleOpt = scheduleRepository.findById(id);
        if (existingScheduleOpt.isEmpty()) {
            throw new RuntimeException("Schedule with ID " + id + " not found");
        }

        Schedule existingSchedule = existingScheduleOpt.get();

        // Cập nhật thông tin nếu có thay đổi
        if (updatedSchedule.getTeacher() != null && updatedSchedule.getTeacher().getTeacher_id() != null) {
            Teacher teacher = teacherRepository.findById(updatedSchedule.getTeacher().getTeacher_id())
                    .orElseThrow(() -> new RuntimeException("Teacher not found"));
            existingSchedule.setTeacher(teacher);
        }

        if (updatedSchedule.getStudentClass() != null && updatedSchedule.getStudentClass().getClass_id() != null) {
            ClassEntity studentClass = classRepository.findById(updatedSchedule.getStudentClass().getClass_id())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            existingSchedule.setStudentClass(studentClass);
        }

        if (updatedSchedule.getCourse() != null && updatedSchedule.getCourse().getCourses_id() != null) {
            Courses courses = courseRepository.findById(updatedSchedule.getCourse().getCourses_id())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            existingSchedule.setCourse(courses);
        }

        if (updatedSchedule.getRoom() != null) {
            existingSchedule.setRoom(updatedSchedule.getRoom());
        }

        if (updatedSchedule.getShift() != null) {
            existingSchedule.setShift(updatedSchedule.getShift());
        }

        if (updatedSchedule.getStart_date() != null) {
            existingSchedule.setStart_date(updatedSchedule.getStart_date());
        }

        if (updatedSchedule.getEnd_date() != null) {
            existingSchedule.setEnd_date(updatedSchedule.getEnd_date());
        }

        return scheduleRepository.save(existingSchedule);
    }


}
