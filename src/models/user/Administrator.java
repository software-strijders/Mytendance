package models.user;

import utils.Utils;
import java.util.UUID;

public class Administrator extends User {

    public Administrator(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password, Utils.idGenerator());
    }

    public Administrator(String firstName, String lastName, String email, String password, UUID userId) {
        super(firstName, lastName, email, password, userId);
    }

    public static void addUser(User newUser) throws IllegalArgumentException {
        if (User.getRegisteredUsers().contains(newUser))
            throw new IllegalArgumentException("De gebruiker bestaat al");
        else
            User.addUser(newUser);
    }
}
