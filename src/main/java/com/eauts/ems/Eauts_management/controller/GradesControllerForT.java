package com.eauts.ems.Eauts_management.controller;

import com.eauts.ems.Eauts_management.model.Grades;
import com.eauts.ems.Eauts_management.service.GradesServiceForT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/grades")
public class GradesControllerForT {

    @Autowired
    private GradesServiceForT gradesService;

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<Grades>> getGradesBySchedule(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(gradesService.getGradesBySchedule(scheduleId));
    }

    @PutMapping("/{gradesId}")
    public ResponseEntity<Grades> updateGrade(@PathVariable Long gradesId,
                                              @RequestBody Map<String, Object> request) {
        Float attendance = Float.parseFloat(request.get("attendance").toString());
        Float midterm = Float.parseFloat(request.get("midterm").toString());
        Float finalExam = Float.parseFloat(request.get("finalExam").toString());
        Long teacherId = Long.parseLong(request.get("teacherId").toString());

        return ResponseEntity.ok(gradesService.updateGrade(gradesId, attendance, midterm, finalExam, teacherId));
    }

    @PostMapping("/initialize/{scheduleId}")
    public ResponseEntity<String> initializeGrades(@PathVariable Long scheduleId) {
        gradesService.initializeGradesForSchedule(scheduleId);
        return ResponseEntity.ok("Danh sách điểm đã được khởi tạo thành công!");
    }

}
