package models.user;

import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Student extends User {

    private List<Class> classes;

    public Student(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, Utils.idGenerator());
    }

    public Student(String firstName, String lastName, String email, String password, UUID userId) {
        super(firstName, lastName, email, password, userId);
        this.classes = new ArrayList<>();
    }

    // We can't avoid this type of construction due to unchecked cast warnings
    public static List<Student> getRegisteredStudents() {
        return User.getRegisteredUsers().stream().filter(user -> user.getClass() == Student.class)
                .map(user -> (Student)user).collect(Collectors.toList());
    }
}
