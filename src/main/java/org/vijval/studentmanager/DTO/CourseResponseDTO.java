
package org.vijval.studentmanager.DTO;

import java.util.List;

public class CourseResponseDTO {

    private Long id;
    private String name;
    private int credits;
    private List<Long> studentIds;

    public CourseResponseDTO() {
    }

    public CourseResponseDTO(Long id, String name,
                             int credits, List<Long> studentIds) {
        this.id = id;
        this.name = name;
        this.credits = credits;
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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
