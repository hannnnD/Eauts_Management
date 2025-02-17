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


}
