package com.eauts.ems.Eauts_management.controller;

import com.eauts.ems.Eauts_management.dto.GradesDTO;
import com.eauts.ems.Eauts_management.dto.ScheduleDTO;
import com.eauts.ems.Eauts_management.service.StudentFuncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentFuncController {
    private final StudentFuncService studentFuncService;

    public StudentFuncController(StudentFuncService studentFuncService) {
        this.studentFuncService = studentFuncService;
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleDTO>> getStudentSchedule(@RequestAttribute("userId") Long userId) {
        return ResponseEntity.ok(studentFuncService.getStudentSchedule(userId));
    }

    @GetMapping("/grades")
    public ResponseEntity<List<GradesDTO>> getStudentGrades(@RequestAttribute("userId") Long userId) {
        return ResponseEntity.ok(studentFuncService.getStudentGrades(userId));
    }
}
