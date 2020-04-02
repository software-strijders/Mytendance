package models.user;

import enums.UserType;
import utils.Utils;
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

    public static void clearUsers() {
        registeredUsers.clear();
    }

    public static User authenticateUser(String email, String password) {
        for (User user : registeredUsers)
            if (user.getEmail().equalsIgnoreCase(email)
                    && user.getPassword().equals(password))
                return user;

        return null;
    }

    public static User authenticateUser(String email, String password, UserType type) {
        for (User user : registeredUsers)
            if (user.getClass() == type.typeClass()
                    && user.getEmail().equalsIgnoreCase(email)
                    && user.getPassword().equals(password))
                return user;

        return null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User user) throws IllegalArgumentException {
        if (user == null)
            throw new IllegalArgumentException("De opgegeven gebruiker bestaat niet :(");
        else
            loggedInUser = user;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof User
                && this.email.equalsIgnoreCase(((User)other).email);
    }

    @Override
    public String toString() {
        return String.format("%s %s", Utils.capitalize(this.firstName), Utils.capitalize(this.lastName));
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
