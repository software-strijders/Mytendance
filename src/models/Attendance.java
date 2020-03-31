package models;

import enums.AttendanceType;
import enums.ReasonType;
import models.user.Student;

import java.util.Collections;
import java.util.List;

public class Attendance {

    private static List<Attendance> attendances;

    private ReasonType reason;
    private String reasonDescription;
    private Student student;
    private AttendanceType attendanceType;

    /**
     * constructor for AttendanceType.PRESENT
     */
    public Attendance(Student student) {
        this(null, null, student, AttendanceType.PRESENT);
    }

    /**
     * constructor for any AttendanceType:
     * Any other attendance type needs a reason with it.
     */
    public Attendance(ReasonType reason, String reasonDescription, Student student, AttendanceType attendanceType) {
        this.reason = reason;
        this.reasonDescription = reasonDescription;
        this.student = student;
        this.attendanceType = attendanceType;
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
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public AttendanceType getAttendanceType() {
        return this.attendanceType;
    }

    public ReasonType getReason() {
        return this.reason;
    }

    public String getReasonDescription() {
        return this.reasonDescription;
    }

    public void setReason(ReasonType reason) {
        this.reason = reason;
    }

    public void setReasonDescription(String reasonDescription) {
        this.reasonDescription = reasonDescription;
    }

    public void setAttendanceType(AttendanceType attendanceType) {
        this.attendanceType = attendanceType;
    }

    @Override
    public String toString() {
        if (this.reason == null) {
            return student.toString();
        }
        else {
            return String.format("%s - %s", this.student, this.reason.typeName());
        }
    }
}
