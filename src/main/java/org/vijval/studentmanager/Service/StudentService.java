
package org.vijval.studentmanager.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.vijval.studentmanager.DTO.*;
import org.vijval.studentmanager.Entity.*;
import org.vijval.studentmanager.Repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository,
                          DepartmentRepository departmentRepository,
                          CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
    }

    public StudentResponseDTO save(StudentRequestDTO request) {
        Student student = new Student();

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());

        Department department = departmentRepository
                .findById(request.getDepartmentId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Department not found"));

        student.setDepartment(department);

        List<Course> courses = new ArrayList<>();

        if (request.getCourseIds() != null) {
            for (Long courseId : request.getCourseIds()) {
                Course course = courseRepository.findById(courseId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Course not found"));

                courses.add(course);
            }
        }

        student.setCourses(courses);

        studentRepository.save(student);

        return convertToDTO(student);
    }

    @Transactional(readOnly = true)
    public List<StudentResponseDTO> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> result = new ArrayList<>();

        for (Student student : students) {
            result.add(convertToDTO(student));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public StudentResponseDTO findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"));

        return convertToDTO(student);
    }

    public StudentResponseDTO update(Long id, StudentRequestDTO request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"));

        student.setName(request.getName());
        student.setAge(request.getAge());
        student.setEmail(request.getEmail());

        Department department = departmentRepository
                .findById(request.getDepartmentId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Department not found"));

        student.setDepartment(department);

        List<Course> courses = new ArrayList<>();

        if (request.getCourseIds() != null) {
            for (Long courseId : request.getCourseIds()) {
                Course course = courseRepository.findById(courseId)
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Course not found"));

                courses.add(course);
            }
        }

        student.setCourses(courses);

        studentRepository.save(student);

        return convertToDTO(student);
    }

    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student not found");
        }

        studentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<CourseResponseDTO> findCoursesByStudentId(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Student not found"));

        List<CourseResponseDTO> result = new ArrayList<>();

        for (Course course : student.getCourses()) {
            List<Long> studentIds = new ArrayList<>();

            for (Student s : course.getStudents()) {
                studentIds.add(s.getId());
            }

            result.add(new CourseResponseDTO(
                    course.getId(),
                    course.getName(),
                    course.getCredits(),
                    studentIds
            ));
        }

        return result;
    }

    private StudentResponseDTO convertToDTO(Student student) {
        Long departmentId = null;
        String departmentName = null;

        if (student.getDepartment() != null) {
            departmentId = student.getDepartment().getId();
            departmentName = student.getDepartment().getName();
        }

        List<Long> courseIds = new ArrayList<>();

        if (student.getCourses() != null) {
            for (Course course : student.getCourses()) {
                courseIds.add(course.getId());
            }
        }

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getEmail(),
                departmentId,
                departmentName,
                courseIds
        );
    }
}
