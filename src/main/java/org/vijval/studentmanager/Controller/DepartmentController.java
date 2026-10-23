
package org.vijval.studentmanager.Controller;

import org.vijval.studentmanager.DTO.DepartmentRequestDTO;
import org.vijval.studentmanager.DTO.DepartmentResponseDTO;
import org.vijval.studentmanager.DTO.StudentResponseDTO;
import org.vijval.studentmanager.Service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
            @RequestBody DepartmentRequestDTO request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(departmentService.save(request));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(departmentService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentRequestDTO request) {

        return ResponseEntity.ok(
                departmentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<List<StudentResponseDTO>> getDepartmentStudents(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                departmentService.findStudentsByDepartmentId(id));
    }
}
