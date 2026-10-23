package org.vijval.studentmanager.dtoignore;

public class StudentRequestDTO {

    private String name;
    private int age;
    private String email;
    private Long department_id;

    public StudentRequestDTO(){

    }

    public StudentRequestDTO(String name, int age, String email, Long department_id) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.department_id = department_id;
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

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }
}
