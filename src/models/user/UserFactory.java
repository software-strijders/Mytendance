package models.user;

import enums.UserType;

public class UserFactory {

    public static User create(String firstName, String lastName, String email, String password, UserType type) {
        switch (type) {
            case STUDENT:
                return new Student(firstName, lastName, email, password);
            case TEACHER:
                return new Teacher(firstName, lastName, email, password);
            default:
                return null;
        }
    }
}
