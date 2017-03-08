package com.olfu.olfudisasterapp.data;

/**
 * Created by mleano on 2/28/2017.
 */

public class AccountInformation {

    private String id;
    private String userNum;
    private int course;
    private String email;
    private String lastName;
    private String firstName;
    private String middleName;
    private String contactNum;
    private int userType;
    private String courseName;
    private String profilePicture;

    public AccountInformation(String id, String userNum, int course, String email, String lastName,
                              String firstName, String middleName, String contactNum, int userType,
                              String courseName, String profilePicture) {
        this.id = id;
        this.userNum = userNum;
        this.course = course;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.contactNum = contactNum;
        this.userType = userType;
        this.courseName = courseName;
        this.profilePicture = profilePicture;
    }

    public String getId() {
        return id;
    }

    public String getUserNum() {
        return userNum;
    }

    public int getCourse() {
        return course;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getContactNum() {
        return contactNum;
    }

    public int getUserType() {
        return userType;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
