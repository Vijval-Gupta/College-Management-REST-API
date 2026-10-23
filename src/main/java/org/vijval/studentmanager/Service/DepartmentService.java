
package org.vijval.studentmanager.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.vijval.studentmanager.DTO.*;
import org.vijval.studentmanager.Entity.*;
import org.vijval.studentmanager.Repository.DepartmentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public DepartmentResponseDTO save(DepartmentRequestDTO request) {
        Department department = new Department();

        department.setName(request.getName());
        department.setCode(request.getCode());

        departmentRepository.save(department);

        return convertToDTO(department);
    }

    @Transactional(readOnly = true)
    public List<DepartmentResponseDTO> findAll() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentResponseDTO> result = new ArrayList<>();

        for (Department department : departments) {
            result.add(convertToDTO(department));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public DepartmentResponseDTO findById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Department not found"));

        return convertToDTO(department);
    }

    public DepartmentResponseDTO update(
            Long id, DepartmentRequestDTO request) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Department not found"));

        department.setName(request.getName());
        department.setCode(request.getCode());

        departmentRepository.save(department);

        return convertToDTO(department);
    }

    public void deleteById(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Department not found");
        }

        departmentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<StudentResponseDTO> findStudentsByDepartmentId(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Department not found"));

        List<StudentResponseDTO> result = new ArrayList<>();

        for (Student student : department.getStudents()) {
            Long departmentId = department.getId();
            String departmentName = department.getName();

            List<Long> courseIds = new ArrayList<>();

            for (Course course : student.getCourses()) {
                courseIds.add(course.getId());
            }

            result.add(new StudentResponseDTO(
                    student.getId(),
                    student.getName(),
                    student.getAge(),
                    student.getEmail(),
                    departmentId,
                    departmentName,
                    courseIds
            ));
        }

        return result;
    }

    private DepartmentResponseDTO convertToDTO(Department department) {
        List<Long> studentIds = new ArrayList<>();

        if (department.getStudents() != null) {
            for (Student student : department.getStudents()) {
                studentIds.add(student.getId());
            }
        }

        return new DepartmentResponseDTO(
                department.getId(),
                department.getName(),
                department.getCode(),
                studentIds
        );
    }
}
