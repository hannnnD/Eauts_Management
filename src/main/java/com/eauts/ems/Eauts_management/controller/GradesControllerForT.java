package com.eauts.ems.Eauts_management.controller;

import com.eauts.ems.Eauts_management.dto.GradesDTO;
import com.eauts.ems.Eauts_management.model.Grades;
import com.eauts.ems.Eauts_management.repository.GradesRepository;
import com.eauts.ems.Eauts_management.service.GradesServiceForT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("teacher/grades")
public class GradesControllerForT {

    @Autowired
    private GradesServiceForT gradesService;

    @Autowired
    private GradesRepository gradesRepository;

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<GradesDTO>> getGradesBySchedule(@PathVariable Long scheduleId) {
        List<Grades> gradesList = gradesService.getGradesBySchedule(scheduleId);

        if (gradesList.isEmpty()) {
            gradesService.initializeGradesForSchedule(scheduleId);
            gradesList = gradesService.getGradesBySchedule(scheduleId);
        }

        List<GradesDTO> response = gradesList.stream()
                .map(GradesDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @Transactional
    public void updateGrade(Long gradesId, float attendance, float midterm, float finalExam, Long teacherId) {
        Grades grades = gradesRepository.findById(gradesId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy điểm số!"));

        if (!grades.getSchedule().getTeacher().getTeacher_id().equals(teacherId)) {
            throw new RuntimeException("Bạn không có quyền cập nhật điểm cho lớp này!");
        }

        float total = attendance * 0.1f + midterm * 0.2f + finalExam * 0.7f;
        gradesRepository.updateGrade(gradesId, attendance, midterm, finalExam, total);
    }

    @PutMapping("/bulk")
    public ResponseEntity<Void> updateMultipleGrades(@RequestBody List<GradesDTO> gradesList) {
        gradesService.updateMultipleGrades(gradesList);
        return ResponseEntity.ok().build();
    }
}