package models.user;

import java.util.UUID;

public class Teacher extends User{

    public Teacher(String email, String password, String firstname, String surname, UUID userId) {
        super(email, password, firstname, surname, userId);
    }
}
