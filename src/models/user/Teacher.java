package models.user;

import java.util.List;
import models.Class;
import models.Lecture;
import utils.Utils;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class Teacher extends User {

    private ArrayList<Class> classes =  new ArrayList<Class>();
    private ArrayList<Lecture> lectures = new ArrayList<Lecture>();

    public Teacher(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, Utils.idGenerator());
    }

    public Teacher(String firstName, String lastName, String email, String password, UUID userId) {
        super(firstName, lastName, email, password, userId);
    }

    // We can't avoid this type of construction due to unchecked cast warnings
    public static List<Teacher> getRegisteredTeachers() {
        return User.getRegisteredUsers().stream().filter(user -> user.getClass() == Teacher.class)
                .map(user -> (Teacher)user).collect(Collectors.toList());
    }

    public ArrayList<Lecture> getLectures() {
        return this.lectures;
    }

    public ArrayList<Class> getAllClasses() {
        return this.classes;
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}
