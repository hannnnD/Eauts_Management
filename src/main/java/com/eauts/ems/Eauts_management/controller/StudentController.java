package com.eauts.ems.Eauts_management.controller;

import com.eauts.ems.Eauts_management.dto.StudentDTO;
import com.eauts.ems.Eauts_management.model.*;
import com.eauts.ems.Eauts_management.service.ClassService;
import com.eauts.ems.Eauts_management.service.StudentService;
import com.eauts.ems.Eauts_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/students")
public class StudentController {

    private final StudentService studentService;
    private final UserService userService;
    private final ClassService classService;

    @Autowired
    public StudentController(StudentService studentService, UserService userService, ClassService classService) {
        this.studentService = studentService;
        this.userService = userService;
        this.classService = classService;
    }

    // 🟢 API lấy danh sách sinh viên
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // 🟢 API lấy sinh viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🟢 API thêm mới sinh viên kèm classId
    @PostMapping
    @Transactional
    public ResponseEntity<?> createStudent(@RequestBody Student studentRequest) {
        if (studentRequest == null || studentRequest.getUser() == null || studentRequest.getStudentClass() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thiếu dữ liệu đầu vào!");
        }

        int enrollmentYear = studentRequest.getEnrollment_year();
        int nextStudentNumber = studentService.getNextStudentNumber(enrollmentYear);
        String formattedUsername = String.format("%d%05d", enrollmentYear, nextStudentNumber);

        User newUser = new User();
        newUser.setUsername(formattedUsername);
        newUser.setPassword(studentRequest.getUser().getPassword());
        newUser.setRole(Role.STUDENT);
        User savedUser = userService.createUser(newUser);

        if (savedUser == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi tạo User!");
        }

        Optional<ClassEntity> classOptional = classService.getClassById(studentRequest.getStudentClass().getClass_id());
        if (classOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lớp học không tồn tại!");
        }

        Student newStudent = new Student();
        newStudent.setFull_name(studentRequest.getFull_name());
        newStudent.setDate_of_birth(studentRequest.getDate_of_birth());
        newStudent.setGender(studentRequest.getGender());
        newStudent.setAddress(studentRequest.getAddress());
        newStudent.setPhone(studentRequest.getPhone());
        newStudent.setEnrollment_year(enrollmentYear);
        newStudent.setStatus(studentRequest.getStatus());
        newStudent.setUser(savedUser);
        newStudent.setStudentClass(classOptional.get()); // Gán lớp học

        Student savedStudent = studentService.createStudent(newStudent);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // 🟢 API cập nhật sinh viên theo ID
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody Student studentRequest) {
        Optional<Student> studentOptional = studentService.getStudentById(id);
        if (studentOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sinh viên không tồn tại!");
        }

        Student existingStudent = studentOptional.get();

        // 🟠 Cập nhật thông tin cá nhân
        existingStudent.setFull_name(studentRequest.getFull_name());
        existingStudent.setDate_of_birth(studentRequest.getDate_of_birth());
        existingStudent.setGender(studentRequest.getGender());
        existingStudent.setAddress(studentRequest.getAddress());
        existingStudent.setPhone(studentRequest.getPhone());
        existingStudent.setStatus(studentRequest.getStatus());

        // 🟠 Kiểm tra nếu cần cập nhật lớp học
        if (studentRequest.getStudentClass() != null) {
            Optional<ClassEntity> classOptional = classService.getClassById(studentRequest.getStudentClass().getClass_id());
            if (classOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Lớp học không tồn tại!");
            }
            existingStudent.setStudentClass(classOptional.get());
        }

        // 🟠 Lưu thay đổi
        Student updatedStudent = studentService.updateStudent(existingStudent);
        return ResponseEntity.ok(updatedStudent);
    }

}
