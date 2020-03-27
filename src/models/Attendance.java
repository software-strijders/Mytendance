package models;

import enums.ReasonType;
import models.user.Student;

public class Attendance {

    private ReasonType reason;
    private String reasonDescription;
    private Student student;

    public Attendance(ReasonType reason, String reasonDescription, Student student) {
        this.reason = reason;
        this.reasonDescription = reasonDescription;
        this.student = student;
    }
}
