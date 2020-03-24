package models.user;

import java.util.UUID;

public class Student extends User{

    public Student(String email, String password, String firstname, String surname, UUID userId) {
        super(email, password, firstname, surname, userId);
    }
}
