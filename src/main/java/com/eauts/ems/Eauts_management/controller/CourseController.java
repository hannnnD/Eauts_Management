package com.eauts.ems.Eauts_management.controller;

import com.eauts.ems.Eauts_management.model.Courses;
import com.eauts.ems.Eauts_management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // 📌 Lấy danh sách khóa học
    @GetMapping
    public ResponseEntity<List<Courses>> getAllCourses() {
        List<Courses> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // 📌 Lấy thông tin khóa học theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable Long id) {
        Optional<Courses> course = courseService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 📌 Thêm khóa học mới
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Courses courses) {
        try {
            Courses newCourses = courseService.createCourse(courses);
            return ResponseEntity.ok(newCourses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 📌 Cập nhật khóa học theo ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody Courses coursesDetails) {
        try {
            Courses updatedCourses = courseService.updateCourse(id, coursesDetails);
            return ResponseEntity.ok(updatedCourses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 📌 Xóa khóa học theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok("Xóa khóa học thành công!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
