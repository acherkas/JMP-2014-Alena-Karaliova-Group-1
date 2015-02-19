package com.jmp.software.design.vo;

public class ViewerVO {

    private final String firstName;
    private final String lastName;

    public ViewerVO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
