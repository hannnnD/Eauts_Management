package com.eauts.ems.Eauts_management.controller;

import com.eauts.ems.Eauts_management.model.ClassEntity;
import com.eauts.ems.Eauts_management.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/classes")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    // API thêm lớp học
    @PostMapping
    public ResponseEntity<ClassEntity> createClass(@RequestBody ClassEntity classEntity) {
        ClassEntity newClass = classService.createClass(classEntity);
        return ResponseEntity.ok(newClass);
    }

    // API lấy danh sách lớp học
    @GetMapping
    public ResponseEntity<List<ClassEntity>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }

    // API lấy lớp học theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ClassEntity> getClassById(@PathVariable Long id) {
        Optional<ClassEntity> classEntity = classService.getClassById(id);
        return classEntity.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // API cập nhật lớp học
    @PutMapping("/{id}")
    public ResponseEntity<ClassEntity> updateClass(@PathVariable Long id, @RequestBody ClassEntity classDetails) {
        ClassEntity updatedClass = classService.updateClass(id, classDetails);
        return ResponseEntity.ok(updatedClass);
    }

    // API xóa lớp học
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
        return ResponseEntity.ok("Lớp học đã được xóa thành công!");
    }
}
