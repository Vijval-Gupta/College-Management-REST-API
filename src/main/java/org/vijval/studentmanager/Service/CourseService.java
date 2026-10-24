
package org.vijval.studentmanager.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.vijval.studentmanager.DTO.*;
import org.vijval.studentmanager.Entity.*;
import org.vijval.studentmanager.Repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponseDTO save(CourseRequestDTO request) {
        Course course = new Course();

        course.setName(request.getName());
        course.setCredits(request.getCredits());

        courseRepository.save(course);

        return convertToDTO(course);
    }

    @Transactional(readOnly = true)
    public List<CourseResponseDTO> findAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseResponseDTO> result = new ArrayList<>();

        for (Course course : courses) {
            result.add(convertToDTO(course));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public CourseResponseDTO findById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"));

        return convertToDTO(course);
    }

    public CourseResponseDTO update(Long id, CourseRequestDTO request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"));

        course.setName(request.getName());
        course.setCredits(request.getCredits());

        courseRepository.save(course);

        return convertToDTO(course);
    }

    public void deleteById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"));

        courseRepository.delete(course);
    }

    @Transactional(readOnly = true)
    public List<StudentResponseDTO> findStudentsByCourseId(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course not found"));

        List<StudentResponseDTO> result = new ArrayList<>();

        for (Student student : course.getStudents()) {
            Long departmentId = null;
            String departmentName = null;

            if (student.getDepartment() != null) {
                departmentId = student.getDepartment().getId();
                departmentName = student.getDepartment().getName();
            }

//            List<Long> courseIds = new ArrayList<>();
//
//            for (Course studentCourse : student.getCourses()) {
//                courseIds.add(studentCourse.getId());
//            }

            result.add(new StudentResponseDTO(
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getEmail(),
                    departmentId,
                    departmentName,
                    new ArrayList<>()
            ));
        }

        return result;
    }

    private CourseResponseDTO convertToDTO(Course course) {
        List<Long> studentIds = new ArrayList<>();

        if (course.getStudents() != null) {
            for (Student student : course.getStudents()) {
                studentIds.add(student.getId());
            }
        }

        return new CourseResponseDTO(
                course.getId(),
                course.getName(),
                course.getCredits(),
                studentIds
        );
    }
}
