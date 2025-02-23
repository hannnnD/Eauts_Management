package com.eauts.ems.Eauts_management.service;

import com.eauts.ems.Eauts_management.dto.ScheduleDTO;
import com.eauts.ems.Eauts_management.model.Teacher;
import com.eauts.ems.Eauts_management.repository.ScheduleRepository;
import com.eauts.ems.Eauts_management.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceForT {

    private final TeacherRepository teacherRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceForT(TeacherRepository teacherRepository, ScheduleRepository scheduleRepository) {
        this.teacherRepository = teacherRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public List<ScheduleDTO> getSchedulesByUserId(Long userId) {
        Optional<Teacher> teacherOpt = teacherRepository.findByUserUserId(userId);

        if (teacherOpt.isEmpty()) {
            throw new RuntimeException("Không tìm thấy giáo viên với user_id: " + userId);
        }

        Teacher teacher = teacherOpt.get();

        return scheduleRepository.findByTeacherTeacherId(teacher.getTeacherId()).stream()
                .map(schedule -> new ScheduleDTO(
                        schedule.getId(),
                        teacher.getFull_name(),
                        schedule.getStudentClass().getClass_name(),
                        schedule.getCourse().getCourseName(),
                        schedule.getRoom(),
                        schedule.getShift().name(),
                        schedule.getStart_date(),
                        schedule.getEnd_date()
                ))
                .collect(Collectors.toList());
    }
}
