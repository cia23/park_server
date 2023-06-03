package Model;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String userID;

    /**
     * empty constructor for User
     */
    public User() {
        username = null;
        password = null;
        userID = null;
    }

    /**
     * User constructor with all parameters
     * @param username
     * @param password
     * @param userID
     */
    public User(String username, String password, String userID) {
        this.username = username;
        this.password = password;
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userID, user.userID));
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, userID);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userID='" + userID + '\'' +
                '}';
    }
}