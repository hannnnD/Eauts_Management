package com.eauts.ems.Eauts_management.service;

import com.eauts.ems.Eauts_management.model.Role;
import com.eauts.ems.Eauts_management.model.Teacher;
import com.eauts.ems.Eauts_management.model.Student;
import com.eauts.ems.Eauts_management.model.User;
import com.eauts.ems.Eauts_management.repository.TeacherRepository;
import com.eauts.ems.Eauts_management.repository.StudentRepository;
import com.eauts.ems.Eauts_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Nếu không có role, mặc định là STUDENT
        if (user.getRole() == null) {
            user.setRole(Role.STUDENT);
        }

        return userRepository.save(user);
    }

    // ✅ Tìm người dùng theo username
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // ✅ Kiểm tra mật khẩu
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // ✅ Xác thực và trả về token với đầy đủ thông tin id, role, teacher_id/student_id
    public String verify(User user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            User authenticatedUser = userRepository.findByUsername(user.getUsername());

            if (authenticatedUser != null) {
                int userId = authenticatedUser.getId();
                String role = authenticatedUser.getRole().name();
                Long teacherId = null;
                Long studentId = null;

                if ("TEACHER".equals(role)) {
                    Optional<Teacher> teacher = teacherRepository.findByEmail(authenticatedUser.getUsername());
                    if (teacher != null) {
                        teacherId = teacher.get().getTeacherId();
                    }
                } else if ("STUDENT".equals(role)) {
                    Student student = studentRepository.findByEmail(authenticatedUser.getUsername());
                    if (student != null) {
                        studentId = student.getStudentId();
                    }
                }

                return jwtService.generateToken(userId, authenticatedUser.getUsername(), role, teacherId, studentId);
            }
        }
        return "fail";
    }

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Mã hóa mật khẩu
        return userRepository.save(user);
    }
}
