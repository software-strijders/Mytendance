package models;

import enums.AttendanceType;
import enums.ReasonType;
import enums.SubjectType;
import models.user.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceTest {

    private Student student;
    Attendance a1;

    @BeforeEach
    void beforeEachTest() {
        student = new Student("firstname", "lastname", "email", "password");
        a1 = new Attendance(ReasonType.ENTOMBMENT, "Ik word begraven", student, AttendanceType.ABSENT);
    }

    @Test
    void testReturnValueOfReasonType() {
        assertEquals(ReasonType.ENTOMBMENT, a1.getReason());
    }

    @Test
    void reasonTypeShouldReturnNullWhenNull() {
        Attendance a2 = new Attendance(null, "null", student, AttendanceType.ABSENT);
        assertNull(a2.getReason());
    }

    @Test
    void testReturnValueOfDescription() {
        assertEquals("Ik word begraven", a1.getReasonDescription());
    }

    @Test
    void descriptionShouldReturnNullWhenNull() {
        Attendance a2 = new Attendance(ReasonType.ENTOMBMENT, null, student, AttendanceType.ABSENT);
        assertNull(a2.getReasonDescription());
    }

    @Test
    void testReturnValueOfStudent() {
        assertEquals(student, a1.getStudent());
    }

    @Test
    void studentShouldReturnNullWhenNull() {
        Attendance a2 = new Attendance(ReasonType.ENTOMBMENT, "null", null, AttendanceType.ABSENT);
        assertNull(a2.getStudent());
    }

    @Test
    void testReturnValueOfAttendanceType() {
        assertEquals(AttendanceType.ABSENT, a1.getAttendanceType());
    }

    @Test
    void attendanceTypeShouldReturnNullWhenNull() {
        Attendance a2 = new Attendance(ReasonType.ENTOMBMENT, "null", student, null);
        assertNull(a2.getAttendanceType());
    }

    @Test
    void attendanceToStringShouldReturnNullWhenNull() {
        assertEquals("Firstname Lastname - ENTOMBMENT", a1.toString());
    }
}
