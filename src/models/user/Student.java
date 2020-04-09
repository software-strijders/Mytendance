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
    private boolean sick;

    public Student(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, Utils.idGenerator());
    }

    public Student(String firstName, String lastName, String email, String password, UUID userId) {
        super(firstName, lastName, email, password, userId);
        this.classes = new ArrayList<>();
        this.sick = false;
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
        return this.sick;
    }

    public void setSick(boolean sick) {
        this.sick = sick;
    }

    public void setUpcomingAttendances(boolean sick) {
        AttendanceType[] status = {AttendanceType.PRESENT, AttendanceType.Absent.ILL};
        this.sick = sick;

        for (Class myClass : this.classes)
            for (Lecture lecture : myClass.getUpcomingLectures()) {
                Attendance attendance = lecture.getAttendanceOfStudent(this);

                if (attendance.getType().equals(status[sick ? 0 : 1])) // Java makes me sad sometimes :(
                    attendance.setType(status[!sick ? 0 : 1]);
            }
    }
}
