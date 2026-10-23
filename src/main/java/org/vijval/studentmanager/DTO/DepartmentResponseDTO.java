
package org.vijval.studentmanager.DTO;

import java.util.List;

public class DepartmentResponseDTO {

    private Long id;
    private String name;
    private String code;
    private List<Long> studentIds;

    public DepartmentResponseDTO() {
    }

    public DepartmentResponseDTO(Long id, String name,
                                 String code, List<Long> studentIds) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.studentIds = studentIds;
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

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
