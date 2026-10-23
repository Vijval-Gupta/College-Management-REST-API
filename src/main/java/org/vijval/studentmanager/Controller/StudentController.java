
package org.vijval.studentmanager.Controller;

import org.vijval.studentmanager.DTO.StudentRequestDTO;
import org.vijval.studentmanager.DTO.StudentResponseDTO;
import org.vijval.studentmanager.DTO.CourseResponseDTO;
import org.vijval.studentmanager.Service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(
            @RequestBody StudentRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDTO request) {

        return ResponseEntity.ok(studentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseResponseDTO>> getStudentCourses(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentService.findCoursesByStudentId(id));
    }
}
