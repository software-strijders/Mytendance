package models.user;

import enums.AttendanceType;
import models.Attendance;
import models.Class;
import models.Lecture;
import utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Student extends User {

    private List<Class> classes;
    private boolean isSick;

    public Student(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, Utils.idGenerator());
    }

    public Student(String firstName, String lastName, String email, String password, UUID userId) {
        super(firstName, lastName, email, password, userId);
        this.classes = new ArrayList<>();
        this.isSick = false;
    }

    // We can't avoid this type of construction due to unchecked cast warnings
    public static List<Student> getRegisteredStudents() {
        return User.getRegisteredUsers().stream().filter(Student.class::isInstance)
                .map(Student.class::cast).collect(Collectors.toList());
    }

    public List<Class> getClasses() {
        return Collections.unmodifiableList(classes);
    }

    public void addClass(Class newClass) {
        this.classes.add(newClass);
    }

    public boolean isSick() {
        return this.isSick;
    }

    public void setSick(boolean sick) {
        this.isSick = sick;
    }

    public void callInSick() {
        for (Class myClass : this.classes)
            for (Lecture lecture : myClass.getUpcomingLectures()) {
                Attendance attendance = lecture.getAttendanceOfStudent(this);

                if (attendance.isPresent())
                    attendance.setType(AttendanceType.Absent.ILL);
            }
        this.isSick = true;
    }

    public void callOffSick() {
        for (Class myClass : this.classes)
            for (Lecture lecture : myClass.getUpcomingLectures()) {
                Attendance attendance = lecture.getAttendanceOfStudent(this);

                if (attendance.isSick())
                    attendance.setType(AttendanceType.PRESENT);
            }
        this.isSick = false;
    }
}
