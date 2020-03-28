package models.user;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Teacher extends User{

    public Teacher(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, UUID.randomUUID());
    }

    public Teacher(String firstName, String lastName, String email, String password, UUID userId) {
        super(firstName, lastName, email, password, userId);
    }

    // We can't avoid this type of construction due to unchecked cast warnings
    public static List<Teacher> getRegisteredTeachers() {
        return User.getRegisteredUsers().stream().filter(user -> user.getClass() == Teacher.class)
                .map(user -> (Teacher)user).collect(Collectors.toList());
    }
}
