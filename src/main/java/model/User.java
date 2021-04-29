package model;

import com.google.gson.annotations.SerializedName;

public class User {
    private Integer mId;
    private String mFirstName;
    private String mLastName;

    public User() {
    }

    public User(Integer id, String firstName, String lastName) {
        this.mId = id;
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        this.mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        this.mLastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + mId +
                ", firstName='" + mFirstName + '\'' +
                ", lastName='" + mLastName + '\'' +
                '}';
    }
}
