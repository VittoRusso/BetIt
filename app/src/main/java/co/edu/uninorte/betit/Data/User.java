package co.edu.uninorte.betit.Data;

import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Visitante on 5/03/2018.
 */

public class User implements Serializable{
    int userId;
    int points;
    String firstName;
    String lastName;
    String username;
    String password;

    public User(int userId, int points, String firstName, String lastName, String username, String password) {
        this.userId = userId;
        this.points = points;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
