
package org.vijval.studentmanager.DTO;

import java.util.List;

public class StudentResponseDTO {

    private Long id;
    private String name;
    private int age;
    private String email;
    private Long departmentId;
    private String departmentName;
    private List<Long> courseIds;

    public StudentResponseDTO() {
    }

    public StudentResponseDTO(Long id, String name, int age,
                              String email, Long departmentId,
                              String departmentName,
                              List<Long> courseIds) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.courseIds = courseIds;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }
}
