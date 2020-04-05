package models.user;

import models.Class;
import models.Lecture;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.Utils;
import java.util.UUID;
import java.util.stream.Collectors;

public class Teacher extends User {

    private List<Class> classes = new ArrayList<>();
    private List<Lecture> lectures = new ArrayList<>();

    public Teacher(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, Utils.idGenerator());
    }

    public Teacher(String firstName, String lastName, String email, String password, UUID userId) {
        super(firstName, lastName, email, password, userId);
    }

    // We can't avoid this type of construction due to unchecked cast warnings
    public static List<Teacher> getRegisteredTeachers() {
        return User.getRegisteredUsers().stream().filter(Teacher.class::isInstance)
                .map(Teacher.class::cast).collect(Collectors.toList());
    }

    public List<Lecture> getLectures() {
        return Collections.unmodifiableList(this.lectures);
    }

    public List<Lecture> getLecturesFromDate(LocalDate date) {
        return this.lectures.stream().filter(lecture ->
                LocalDate.from(lecture.getStartDate()).isEqual(date)).collect(Collectors.toList());
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
}
