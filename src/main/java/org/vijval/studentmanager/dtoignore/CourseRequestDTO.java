package org.vijval.studentmanager.dtoignore;

public class CourseRequestDTO {
    private String name;
    private int credits;

    public CourseRequestDTO(){

    }

    public CourseRequestDTO(String name, int credits) {
        this.name = name;
        this.credits = credits;
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
}
