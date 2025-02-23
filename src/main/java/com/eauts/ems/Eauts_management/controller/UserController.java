package com.eauts.ems.Eauts_management.controller;

import com.eauts.ems.Eauts_management.model.Teacher;
import com.eauts.ems.Eauts_management.model.Student;
import com.eauts.ems.Eauts_management.model.User;
import com.eauts.ems.Eauts_management.repository.TeacherRepository;
import com.eauts.ems.Eauts_management.repository.StudentRepository;
import com.eauts.ems.Eauts_management.service.JWTService;
import com.eauts.ems.Eauts_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User foundUser = userService.findByUsername(user.getUsername());

        if (foundUser == null || !userService.checkPassword(user.getPassword(), foundUser.getPassword())) {
            return "Invalid credentials";
        }

        int userId = foundUser.getId();
        String username = foundUser.getUsername();
        String role = String.valueOf(foundUser.getRole());
        Long teacherId = null;
        Long studentId = null;

        if ("TEACHER".equals(role)) {
            Optional<Teacher> teacher = teacherRepository.findByEmail(username);
            if (teacher.isPresent()) { // ✅ Kiểm tra trước khi gọi get()
                teacherId = teacher.get().getTeacherId();
            }
        } else if ("STUDENT".equals(role)) {
            Student student = studentRepository.findByEmail(username);
            if (student != null) {
                studentId = student.getStudent_id();
            }
        }

        return jwtService.generateToken(userId, username, role, teacherId, studentId);
    }
}
