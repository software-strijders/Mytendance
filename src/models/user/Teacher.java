package models.user;

import models.Class;
import models.Lecture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.Utils;

import java.util.UUID;
import java.util.stream.Collectors;

public class Teacher extends User {

    private List<Class> classes =  new ArrayList<>();
    private List<Lecture> lectures = new ArrayList<>();

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

    public List<Lecture> getLectures() {
        return Collections.unmodifiableList(this.lectures);
    }

    public void addLecture(Lecture lecture) {
        this.lectures.add(lecture);
    }

    public List<Class> getAllClasses() {
        return Collections.unmodifiableList(this.classes);
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }

    public void addClass(Class newClass) {
        this.classes.add(newClass);
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, lastName);
    }
}
