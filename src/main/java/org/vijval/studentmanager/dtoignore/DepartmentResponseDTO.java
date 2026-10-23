
package org.vijval.studentmanager.dtoignore;

import java.util.List;

public class DepartmentResponseDTO {

    private Long id;
    private String name;
    private String code;
    private List<StudentSummaryDTO> students;

    public DepartmentResponseDTO() {
    }

    public DepartmentResponseDTO(Long id, String name, String code,
                                 List<StudentSummaryDTO> students) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<StudentSummaryDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentSummaryDTO> students) {
        this.students = students;
    }
}
