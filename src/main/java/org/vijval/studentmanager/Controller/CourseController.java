
package org.vijval.studentmanager.Controller;

import org.vijval.studentmanager.DTO.CourseRequestDTO;
import org.vijval.studentmanager.DTO.CourseResponseDTO;
import org.vijval.studentmanager.DTO.StudentResponseDTO;
import org.vijval.studentmanager.Service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(
            @RequestBody CourseRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(courseService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseRequestDTO request) {

        return ResponseEntity.ok(courseService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentResponseDTO>> getCourseStudents(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                courseService.findStudentsByCourseId(id));
    }
}
