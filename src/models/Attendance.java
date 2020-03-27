package models;

import enums.AttendanceType;
import enums.ReasonType;
import models.user.Student;

public class Attendance {

    private ReasonType reason;
    private String reasonDescription;
    private Student student;
    private AttendanceType attendanceType;

    /**
     * constructor for any AttendanceType
     */
    public Attendance(ReasonType reason, String reasonDescription, Student student, AttendanceType attendanceType) {
        this.reason = reason;
        this.reasonDescription = reasonDescription;
        this.student = student;
        this.attendanceType = attendanceType;
    }

    /**
     * constructor for AttendanceType.PRESENT
     */
    public Attendance(Student student) {
        this.student = student;
        this.attendanceType = AttendanceType.PRESENT;
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
            return String.format("%s - %s", this.student, this.reason);
        }
    }
}
