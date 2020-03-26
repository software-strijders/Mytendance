package models.user;

import java.util.ArrayList;
import java.util.UUID;

public class Student extends User {

    private ArrayList<Class> classes = new ArrayList<>();

    public Student(String email, String password, String firstname, String surname, UUID userId) {
        super(email, password, firstname, surname, userId);
    }

    public static ArrayList<Student> getRegisteredStudents() {
        ArrayList<User> users = User.getRegisteredUsers();
        users.removeIf(user -> user instanceof Teacher);

        ArrayList<Student> students = new ArrayList<>();
        for (User user : users) {
            students.add((Student)user);
        }

        return students;
    }

    public String toString() {
        return String.format("%s %s", this.firstname, this.surname);
    }

}
