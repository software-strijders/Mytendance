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
        this.student = new Student("firstname", "lastname", "email", "password");
        this.a1 = new Attendance(this.student, AttendanceType.Absent.ENTOMBMENT, "Ik word begraven");
    }

    @Test
    void testReturnValueOfAttendanceType() {
        assertEquals(AttendanceType.Absent.ENTOMBMENT, this.a1.getType());
    }

    @Test
    void attendanceTypeShouldReturnPresentByDefault() {
        Attendance a2 = new Attendance(this.student);
        assertEquals(AttendanceType.PRESENT, a2.getType());
    }

    @Test
    void testReturnValueOfDescription() {
        assertEquals("Ik word begraven", this.a1.getDescription());
    }

    @Test
    void descriptionShouldReturnMissingReasonMessageByDefault() {
        Attendance a2 = new Attendance(this.student);
        assertEquals("Er is geen reden opgegeven.", a2.getDescription());
    }

    @Test
    void testReturnValueOfStudent() {
        assertEquals(this.student, this.a1.getStudent());
    }

    @Test
    void studentShouldReturnNullWhenNull() {
        Attendance a2 = new Attendance(null);
        assertNull(a2.getStudent());
    }

    @Test
    void attendanceTypeShouldReturnNullWhenNull() {
        Attendance a2 = new Attendance(this.student, null, "");
        assertNull(a2.getType());
    }

    @Test
    void toStringShouldReturnNameAndTypeOnAbsent() {
        assertEquals("Firstname Lastname - Mummificatie", this.a1.toString());
    }

    @Test
    void toStringShouldReturnNameOnPresent() {
        Attendance a2 = new Attendance(this.student, AttendanceType.PRESENT, "");
        assertEquals("Firstname Lastname", a2.toString());
    }

    @Test
    void isPresentShouldReturnTrueByDefault() {
        Attendance a2 = new Attendance(this.student);
        assertTrue(a2.isPresent());
    }

    @Test
    void isPresentShouldReturnTrueOnPresent() {
        Attendance a2 = new Attendance(this.student, AttendanceType.PRESENT, "");
        assertTrue(a2.isPresent());
    }

    @Test
    void isPresentShouldReturnFalseOnAbsent() {
        Attendance a2 = new Attendance(this.student, AttendanceType.ABSENT, "");
        assertFalse(a2.isPresent());
    }

    @Test
    void isPresentShouldReturnFalseOnOther() {
        Attendance a2 = new Attendance(this.student, AttendanceType.Absent.OTHER, "");
        assertFalse(a2.isPresent());
    }

    @Test
    void attendanceTypeShouldReturnPresentOnPresent() {
        Attendance a2 = new Attendance(this.student, AttendanceType.PRESENT, "");
        assertEquals(AttendanceType.PRESENT, a2.getType());
    }

    @Test
    void attendanceTypeShouldReturnAbsentOnAbsent() {
        Attendance a2 = new Attendance(this.student, AttendanceType.ABSENT, "");
        assertEquals(AttendanceType.ABSENT, a2.getType());
    }

    @Test
    void attendanceTypeShouldReturnOtherOnOther() {
        Attendance a2 = new Attendance(this.student, AttendanceType.Absent.OTHER, "");
        assertEquals(AttendanceType.Absent.OTHER, a2.getType());
    }

    @Test
    void instanceControlledListShouldAddAttendance() {
        Attendance a2 = new Attendance(this.student);
        Attendance.clearAttendances();
        Attendance.addAttendance(a2);
        assertEquals(a2, Attendance.getAttendances().get(0));
    }

    @Test
    void instanceControlledListEmptyOnClear() {
        Attendance a2 = new Attendance(this.student);
        Attendance.addAttendance(a2);
        Attendance.clearAttendances();
        assertTrue(Attendance.getAttendances().isEmpty());
    }
}
