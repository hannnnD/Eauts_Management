package com.eauts.ems.Eauts_management.service;

import com.eauts.ems.Eauts_management.model.Teacher;
import com.eauts.ems.Eauts_management.model.User;
import com.eauts.ems.Eauts_management.repository.TeacherRepository;
import com.eauts.ems.Eauts_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    // Constructor Injection
    public TeacherService(TeacherRepository teacherRepository, UserRepository userRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
    }
    // Lấy danh sách tất cả giảng viên
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Tìm giảng viên theo ID
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    // Thêm mới giảng viên

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Cập nhật thông tin giảng viên
    public Teacher updateTeacher(Long id, Teacher newTeacher) {
        return teacherRepository.findById(id).map(teacher -> {
            teacher.setFull_name(newTeacher.getFull_name());
            teacher.setDate_of_birth(newTeacher.getDate_of_birth());
            teacher.setGender(newTeacher.getGender());
            teacher.setAddress(newTeacher.getAddress());
            teacher.setPhone(newTeacher.getPhone());
            teacher.setEmail(newTeacher.getEmail());
            return teacherRepository.save(teacher);
        }).orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên với ID: " + id));
    }

    // Xóa giảng viên theo ID
    @Transactional
    public void deleteTeacher(Long id) {
        // Tìm giảng viên theo ID
        Optional<Teacher> teacherOpt = teacherRepository.findById(id);

        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            User user = teacher.getUser(); // Lấy User liên kết với Teacher

            // Xóa giảng viên trước
            teacherRepository.delete(teacher);

            // Sau đó xóa User (nếu có)
            if (user != null) {
                userRepository.delete(user);
            }
        } else {
            throw new RuntimeException("Giảng viên không tồn tại với ID: " + id);
        }
    }
}
