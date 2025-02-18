package com.eauts.ems.Eauts_management.service;

import com.eauts.ems.Eauts_management.model.Courses;
import com.eauts.ems.Eauts_management.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Courses> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Courses> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Courses createCourse(Courses courses) {
        if (courseRepository.existsByCourseCode(courses.getCourseCode())) {
            throw new RuntimeException("Mã khóa học đã tồn tại!");
        }
        return courseRepository.save(courses);
    }

    public Courses updateCourse(Long id, Courses coursesDetails) {
        Optional<Courses> courseOpt = courseRepository.findById(id);
        if (courseOpt.isEmpty()) {
            throw new RuntimeException("Khóa học không tồn tại!");
        }

        Courses existingCourses = courseOpt.get();
        existingCourses.setCourseName(coursesDetails.getCourseName());
        existingCourses.setCredits(coursesDetails.getCredits());
        existingCourses.setDescription(coursesDetails.getDescription());
        existingCourses.setTuitionFeePerCredit(coursesDetails.getTuitionFeePerCredit());

        return courseRepository.save(existingCourses);
    }

    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Khóa học không tồn tại!");
        }
        courseRepository.deleteById(id);
    }
}
