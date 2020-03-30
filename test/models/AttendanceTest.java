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
    private Student student = new Student("firstname", "lastname", "email",
            "password", Utils.idGenerator());
    Attendance a1 = new Attendance(ReasonType.ENTOMBMENT,
            "Ik word begraven", student, AttendanceType.ABSENT);

    @Test
    void testgetreasonType() {
        assertEquals(ReasonType.ENTOMBMENT, a1.getReason());
    }

    @Test
    void testReasonTypeWhenReasonNull() {
        Attendance a2 = new Attendance(null, "null", student, AttendanceType.ABSENT);
        assertNull(a2.getReason());
    }

    @Test
    void testDescription(){
        assertEquals("Ik word begraven", a1.getReasonDescription());
    }

    @Test
    void testDescriptionWhenNull(){
        Attendance a2 = new Attendance(ReasonType.ENTOMBMENT, null, student, AttendanceType.ABSENT);
        assertNull(a2.getReasonDescription());
    }

    @Test
    void testStudent(){
        assertEquals(student, a1.getStudent());
    }

    @Test
    void testStudentWhenNull() {
        Attendance a2 = new Attendance(ReasonType.ENTOMBMENT, "null", null, AttendanceType.ABSENT);
        assertNull(a2.getStudent());
    }

    @Test
    void testAttendanceType() {
        assertEquals(AttendanceType.ABSENT, a1.getAttendanceType());
    }

    @Test
    void testAttendanceTypeWhenNull() {
        Attendance a2 = new Attendance(ReasonType.ENTOMBMENT, "null", student, null);
        assertNull(a2.getAttendanceType());
    }

    @Test
    void testToString(){
        assertEquals(a1.toString(), student + " - " + a1.getReason());
    }

}