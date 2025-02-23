package com.eauts.ems.Eauts_management.service;

import com.eauts.ems.Eauts_management.dto.StudentDTO;
import com.eauts.ems.Eauts_management.model.*;
import com.eauts.ems.Eauts_management.repository.StudentRepository;
import com.eauts.ems.Eauts_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<StudentDTO> getStudentByIdU(Long id) {
        return studentRepository.findById(id).map(this::convertToDTO);
    }

    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(
                student.getStudent_id(),
                student.getFull_name(),
                student.getDate_of_birth(),
                student.getGender().name(),
                student.getAddress(),
                student.getPhone(),
                student.getEnrollment_year(),
                student.getStatus().name(),
                (long) student.getUser().getId(),  // Lấy user_id từ User
                student.getUser().getUsername(), // Lấy username từ User
                student.getStudentClass().getClass_id(), // Lấy class_id từ StudentClass
                student.getStudentClass().getMajor_name(), // Lấy major_name từ StudentClass
                student.getStudentClass().getClass_name() // Lấy class_name từ StudentClass
        );
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> new StudentDTO(
                student.getStudent_id(),
                student.getFull_name(),
                student.getDate_of_birth(),
                student.getGender().name(),
                student.getAddress(),
                student.getPhone(),
                student.getEnrollment_year(),
                student.getStatus().name(),
                (long) student.getUser().getId(),
                student.getUser().getUsername(),
                student.getStudentClass().getClass_id(),
                student.getStudentClass().getMajor_name(),
                student.getStudentClass().getClass_name()
        )).collect(Collectors.toList());
    }

    public int getNextStudentNumber(int enrollmentYear) {
        Integer maxNumber = studentRepository.findMaxStudentNumberByYear(enrollmentYear);
        return (maxNumber == null) ? 1 : maxNumber + 1;
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

//    public double calculateTotalTuitionFee(Long studentId) {
//        Optional<Student> studentOpt = studentRepository.findById(studentId);
//        if (studentOpt.isEmpty()) {
//            throw new RuntimeException("Sinh viên không tồn tại!");
//        }
//
//        Student student = studentOpt.get();
//        ClassEntity studentClass = student.getStudentClass();
//
//        if (studentClass == null) {
//            throw new RuntimeException("Sinh viên chưa được phân lớp!");
//        }
//
//        List<Schedule> classSchedules = scheduleRepository.findByClassId(studentClass.getClass_id());
//
//        double totalFee = 0;
//        for (Schedule schedule : classSchedules) {
//            Course course = schedule.getCourse();
//            totalFee += course.calculateTuitionFee();
//        }
//
//        return totalFee;
//    }

}
