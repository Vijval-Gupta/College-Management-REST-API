
package org.vijval.studentmanager.DTO;

public class CourseRequestDTO {

    private String name;
    private int credits;

    public CourseRequestDTO() {
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
