package models.user;

import java.util.UUID;

public class Administrator extends User {

    public Administrator(String email, String password, String firstname, String surname, UUID userId) {
        super(email, password, firstname, surname, userId);
    }

    public static void addUser(User newUser) throws IllegalArgumentException {
        if (!User.getRegisteredUsers().contains(newUser)) {
            User.getRegisteredUsers().add(newUser);
        } else {
            throw new IllegalArgumentException("User bestaat al");
        }

        System.out.println(User.getRegisteredUsers());
    }
}
