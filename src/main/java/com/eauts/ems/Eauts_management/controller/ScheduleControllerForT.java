package com.eauts.ems.Eauts_management.controller;

import com.eauts.ems.Eauts_management.dto.ScheduleDTO;
import com.eauts.ems.Eauts_management.service.ScheduleServiceForT;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher/schedule")
public class ScheduleControllerForT {

    private final ScheduleServiceForT scheduleService;

    public ScheduleControllerForT(ScheduleServiceForT scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getScheduleForTeacher(@RequestAttribute("userId") Long userId) {
        List<ScheduleDTO> schedules = scheduleService.getSchedulesByUserId(userId);
        return ResponseEntity.ok(schedules);
    }
}
