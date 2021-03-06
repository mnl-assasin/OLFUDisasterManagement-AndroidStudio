package com.olfu.olfudisasterapp.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mykelneds on 25/02/2017.
 */

public class ResponseLogin {

    @SerializedName("id")
    private String id;

    @SerializedName("user_number")
    private String userNum;

    @SerializedName("course")
    private String course;

    @SerializedName("email")
    private String email;

    @SerializedName("last_name")
    private String lastName;

    @SerializedName("first_name")
    private String firstName;

    @SerializedName("middle_name")
    private String middleName;

    @SerializedName("contact_number")
    private String contactNum;

    @SerializedName("user_type")
    private String userType;

    @SerializedName("course_name")
    private String courseName;

    @SerializedName("profile_picture_full")
    private String profilePicture;

    public ResponseLogin(String id, String userNum, String course, String email, String lastName, String firstName, String middleName, String contactNum, String userType, String courseName, String profilePicture) {
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

    public String getCourse() {
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

    public String getUserType() {
        return userType;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
