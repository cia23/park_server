package Model;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String id;

    /**
     * empty constructor for User
     */
    public User() {
        username = null;
        password = null;
        id = null;
    }

    /**
     * User constructor with all parameters
     * @param username
     * @param password
     * @param id
     */
    public User(String username, String password, String id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
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
                Objects.equals(id, user.id));
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, id);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", personID='" + id + '\'' +
                '}';
    }
}