package org.vijval.studentmanager.dtoignore;

public class CourseSummaryDTO {
    private Long id;
    private String name;
    private int credits;

    public CourseSummaryDTO(){

    }

    public CourseSummaryDTO(Long id, String name, int credits) {
        this.id = id;
        this.name = name;
        this.credits = credits;
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
}
