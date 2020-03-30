package models.user;

import enums.UserType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class User {

    private static List<User> registeredUsers = new ArrayList<>();
    private static User loggedInUser = null;

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String password;
    protected UUID userId;

    public User(String firstName, String lastName, String email, String password, UUID userId) {

        // NEED TO MAKE CHECKS FOR REGISTERING
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userId = userId;
    }

    public static List<User> getRegisteredUsers() {
        return Collections.unmodifiableList(registeredUsers);
    }

    public static List<User> getRegisteredUsers(UserType type) {
        return registeredUsers.stream().filter(user ->
                user.getClass() == type.typeClass()).collect(Collectors.toList());
    }

    public static void addUser(User user) {
        registeredUsers.add(user);
    }

    public static User authenticateUser(String email, String password) {
        for (User user : registeredUsers)
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                return user;

        return null;
    }

    public static User authenticateUser(String email, String password, UserType type) {
        for (User user : registeredUsers)
            if (user.getClass() == type.typeClass()
                    && user.getEmail().equals(email)
                    && user.getPassword().equals(password))
                return user;

        return null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) throws IllegalArgumentException {
        if (user == null)
            throw new IllegalArgumentException("The user specified is invalid :(");
        else
            loggedInUser = user;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof User
                && this.email.equals(((User)other).email);
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
