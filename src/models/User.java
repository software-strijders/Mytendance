package models;

import java.util.ArrayList;
import java.util.UUID;

public abstract class User {

    private static ArrayList<User> registeredUsers = new ArrayList<>();

    protected String email;
    protected String password;
    protected String firstname;
    protected String surname;
    protected UUID userId;

    public User(String email, String password, String firstname, String surname, UUID userId){
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.surname = surname;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public static ArrayList<User> getRegisteredUsers() {
        return registeredUsers;
    }

    public static void addUser(User newUser) {
        if (!registeredUsers.contains(newUser)) {
            registeredUsers.add(newUser);
        } else {
            throw new IllegalArgumentException("User bestaat al");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                email.equals(user.email);
    }
}
