package models;

import enums.AttendanceType;
import enums.SubjectType;
import models.user.Student;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Attendance {

    private static List<Attendance> attendances = new ArrayList<>();

    private AttendanceType type;
    private String description;
    private Student student;
    private Lecture lecture;

    public Attendance(Lecture lecture, Student student) {
        this(lecture, student, student.isSick() ? AttendanceType.Absent.ILL : AttendanceType.PRESENT, "");
    }

    public Attendance(Lecture lecture, Student student, AttendanceType type, String description) {
        this.lecture = lecture;
        this.student = student;
        this.type = type;
        this.description = description;
    }

    public static List<Attendance> getAttendancesByStudent(Student student) {
        return attendances.stream().filter(attendance ->
                attendance.getStudent().equals(student)).collect(Collectors.toList());
    }

    public static void addAttendance(Attendance attendance) {
        attendances.add(attendance);
    }

    public Lecture getLecture() {
        return lecture;
    }

    public static void clearAttendances() {
        attendances.clear();
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    // Required by the TableView of AdjustAttendanceController
    public AttendanceType getType() {
        return this.type;
    }

    public void setType(AttendanceType type) {
        this.type = type;
    }

    public boolean isPresent() {
        return this.type.equals(AttendanceType.PRESENT);
    }

    public String getDescription() {
        return this.description.isEmpty() ? "Er is geen reden opgegeven." : this.description;
    }

    // Required by the TableView of AdjustAttendanceController
    public SubjectType getLectureSubject() {
        return this.lecture.getSubject();
    }

    // Required by the TableView of AdjustAttendanceController
    public LocalTime getLectureDate() {
        return this.lecture.getStartDate().toLocalTime();
    }

    public List<Attendance> getAttendancesByDate(LocalDate date) {
        return attendances.stream().filter(attendance -> attendance.getLecture()
                .getStartDate().toLocalDate().isEqual(date)).collect(Collectors.toList());
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
