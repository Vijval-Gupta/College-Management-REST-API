
package org.vijval.studentmanager.dtoignore;

public class StudentResponseDTO {

    private Long id;
    private String name;
    private int age;
    private String email;
    private DepartmentSummaryDTO department;

    public StudentResponseDTO() {
    }

    public StudentResponseDTO(Long id, String name, int age,
                              String email, DepartmentSummaryDTO department) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.department = department;
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

    public DepartmentSummaryDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentSummaryDTO department) {
        this.department = department;
    }
}
