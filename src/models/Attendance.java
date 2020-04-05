package models;

import enums.AttendanceType;
import models.user.Student;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Attendance {

    private static List<Attendance> attendances = new ArrayList<>();

    private AttendanceType type;
    private String description;
    private Student student;

    public Attendance(Student student) {
        this(student, AttendanceType.PRESENT, "");
    }

    public Attendance(Student student, AttendanceType type, String description) {
        this.student = student;
        this.type = type;
        this.description = description;
    }

    public static List<Attendance> getAttendances() {
        return Collections.unmodifiableList(attendances);
    }

    public static void addAttendance(Attendance attendance) {
        attendances.add(attendance);
    }

    public static void clearAttendance() {
        attendances.clear();
    }

    public static void setAttendances(List<Attendance> attendances) {
        Attendance.attendances = attendances;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AttendanceType getType() {
        return this.type;
    }

    public void setType(AttendanceType type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        if (this.type == AttendanceType.PRESENT)
            return student.toString();
        else
            return String.format("%s - %s", this.student, this.type);
    }
}
