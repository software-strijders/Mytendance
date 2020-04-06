package models;

import enums.AttendanceType;
import models.user.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceTest {

    private Student student;
    Attendance a1;

    @BeforeEach
    void beforeEachTest() {
        student = new Student("firstname", "lastname", "email", "password");
        a1 = new Attendance(student, AttendanceType.Absent.ENTOMBMENT, "Ik word begraven");
    }

    @Test
    void testReturnValueOfAttendanceType() {
        assertEquals(AttendanceType.Absent.ENTOMBMENT, a1.getType());
    }

    @Test
    void attendanceTypeShouldReturnPresentByDefault() {
        Attendance a2 = new Attendance(student);
        assertEquals(AttendanceType.PRESENT, a2.getType());
    }

    @Test
    void testReturnValueOfDescription() {
        assertEquals("Ik word begraven", a1.getDescription());
    }

    @Test
    void descriptionShouldReturnEmptyByDefault() {
        Attendance a2 = new Attendance(student);
        assertEquals("", a2.getDescription());
    }

    @Test
    void testReturnValueOfStudent() {
        assertEquals(student, a1.getStudent());
    }

    @Test
    void studentShouldReturnNullWhenNull() {
        Attendance a2 = new Attendance(null);
        assertNull(a2.getStudent());
    }

    @Test
    void attendanceTypeShouldReturnNullWhenNull() {
        Attendance a2 = new Attendance(student, null, "");
        assertNull(a2.getType());
    }

    @Test
    void testReturnValueAttendanceToString() {
        assertEquals("Firstname Lastname - Mummificatie", a1.toString());
    }
}
