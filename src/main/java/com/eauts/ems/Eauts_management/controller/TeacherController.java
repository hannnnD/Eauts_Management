package com.eauts.ems.Eauts_management.controller;

import com.eauts.ems.Eauts_management.model.Role;
import com.eauts.ems.Eauts_management.model.Teacher;
import com.eauts.ems.Eauts_management.model.User;
import com.eauts.ems.Eauts_management.service.TeacherService;
import com.eauts.ems.Eauts_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") // Cho phép frontend truy cập
@RestController
@RequestMapping("/admin/teachers")
public class TeacherController {

    private final TeacherService teacherService;
    private final UserService userService;

    @Autowired
    public TeacherController(TeacherService teacherService, UserService userService) {
        this.teacherService = teacherService;
        this.userService = userService;
    }

    // API lấy danh sách giảng viên
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    // API lấy giảng viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // API thêm mới giảng viên
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacherRequest) {
        // Tạo User mới
        User newUser = new User();
        newUser.setUsername(teacherRequest.getUser().getUsername());
        newUser.setPassword(teacherRequest.getUser().getPassword()); // Mã hóa password ở UserService
        newUser.setRole(Role.TEACHER);

        // Lưu User vào database
        User savedUser = userService.createUser(newUser);

        // Tạo Teacher và liên kết với User đã tạo
        teacherRequest.setUser(savedUser);
        Teacher savedTeacher = teacherService.createTeacher(teacherRequest);

        return ResponseEntity.ok(savedTeacher);
    }

    // API cập nhật giảng viên
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher newTeacher) {
        Teacher updatedTeacher = teacherService.updateTeacher(id, newTeacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    // API xóa giảng viên
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
