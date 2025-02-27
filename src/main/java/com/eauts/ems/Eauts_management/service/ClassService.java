package com.eauts.ems.Eauts_management.service;

import com.eauts.ems.Eauts_management.dto.ClassDTO;
import com.eauts.ems.Eauts_management.model.ClassEntity;
import com.eauts.ems.Eauts_management.repository.ClassRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    // Thêm lớp học mới
    public ClassEntity createClass(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }

    // Lấy danh sách tất cả lớp học
    public List<ClassDTO> getAllClassesAsDTO() {
        List<ClassEntity> classEntities = classRepository.findAll();
        return classEntities.stream()
                .map(classEntity -> new ClassDTO(
                        classEntity.getClassId(),
                        classEntity.getClass_name(),
                        classEntity.getMajor_name(),
                        classEntity.getNote()))
                .collect(Collectors.toList());
    }

    // Lấy thông tin lớp học theo ID
    public Optional<ClassEntity> getClassById(Long id) {
        return classRepository.findById(id);
    }

    // Cập nhật lớp học
    public ClassEntity updateClass(Long id, ClassEntity classDetails) {
        return classRepository.findById(id).map(existingClass -> {
            existingClass.setClass_name(classDetails.getClass_name());
            existingClass.setMajor_name(classDetails.getMajor_name());
            existingClass.setNote(classDetails.getNote());
            return classRepository.save(existingClass);
        }).orElseThrow(() -> new RuntimeException("Class not found with id " + id));
    }

    // Xóa lớp học
    public void deleteClass(Long id) {
        if (classRepository.existsById(id)) {
            classRepository.deleteById(id);
        } else {
            throw new RuntimeException("Class not found with id " + id);
        }
    }
}
