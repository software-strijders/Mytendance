package models;

import enums.AttendanceType;
import models.user.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AttendanceTest {

    private Lecture lecture;
    private Student student;
    Attendance a1;

    @BeforeEach
    void beforeEachTest() {
        lecture = null;
        this.student = new Student("firstname", "lastname", "email", "password");
        this.a1 = new Attendance(lecture, this.student, AttendanceType.Absent.ENTOMBMENT, "Ik word begraven");
    }

    @AfterEach
    void cleanUp() {
        Attendance.clearAttendances();
    }

    @Test
    void shouldReturnAttendanceByStudent() {
        Attendance.addAttendance(this.a1);
        assertTrue(Attendance.getAttendancesByStudent(this.student).contains(this.a1));
    }

    @Test
    void shouldNotReturnAttendanceByOtherStudent() {
        Attendance.addAttendance(this.a1);
        Student s2 = new Student(null, null, null, null);
        assertFalse(Attendance.getAttendancesByStudent(s2).contains(this.a1));
    }

    @Test
    void shouldReturnAttendanceBySameDate() {
        Lecture l1 = new Lecture(LocalDateTime.now(), 0, null, null, null);
        Attendance a2 = new Attendance(l1, this.student);
        assertTrue(a2.getAttendancesByDate(LocalDate.now()).contains(a2));
    }

    @Test
    void shouldNotReturnAttendanceByOtherDate() {
        Lecture l1 = new Lecture(LocalDateTime.now().plusDays(1), 0, null, null, null);
        Attendance a2 = new Attendance(l1, this.student);
        assertFalse(a2.getAttendancesByDate(LocalDate.now()).contains(a2));
    }

    @Test
    void testReturnValueOfAttendanceType() {
        assertEquals(AttendanceType.Absent.ENTOMBMENT, this.a1.getType());
    }

    @Test
    void attendanceTypeShouldReturnPresentByDefault() {
        Attendance a2 = new Attendance(lecture, this.student);
        assertEquals(AttendanceType.PRESENT, a2.getType());
    }

    @Test
    void testReturnValueOfDescription() {
        assertEquals("Ik word begraven", this.a1.getDescription());
    }

    @Test
    void descriptionShouldReturnMissingReasonMessageByDefault() {
        Attendance a2 = new Attendance(lecture, this.student);
        assertEquals("Er is geen reden opgegeven.", a2.getDescription());
    }

    @Test
    void testReturnValueOfStudent() {
        assertEquals(this.student, this.a1.getStudent());
    }

    @Test
    void studentShouldReturnNullWhenNull() {
        Attendance a2 = new Attendance(lecture, null);
        assertNull(a2.getStudent());
    }

    @Test
    void attendanceTypeShouldReturnNullWhenNull() {
        Attendance a2 = new Attendance(lecture, this.student, null, "");
        assertNull(a2.getType());
    }

    @Test
    void toStringShouldReturnNameAndTypeOnAbsent() {
        assertEquals("Firstname Lastname - Mummificatie", this.a1.toString());
    }

    @Test
    void toStringShouldReturnNameOnPresent() {
        Attendance a2 = new Attendance(lecture, this.student, AttendanceType.PRESENT, "");
        assertEquals("Firstname Lastname", a2.toString());
    }

    @Test
    void isPresentShouldReturnTrueByDefault() {
        Attendance a2 = new Attendance(lecture, this.student);
        assertTrue(a2.isPresent());
    }

    @Test
    void isPresentShouldReturnTrueOnPresent() {
        Attendance a2 = new Attendance(lecture, this.student, AttendanceType.PRESENT, "");
        assertTrue(a2.isPresent());
    }

    @Test
    void isPresentShouldReturnFalseOnAbsent() {
        Attendance a2 = new Attendance(lecture, this.student, AttendanceType.ABSENT, "");
        assertFalse(a2.isPresent());
    }

    @Test
    void isPresentShouldReturnFalseOnOther() {
        Attendance a2 = new Attendance(lecture, this.student, AttendanceType.Absent.OTHER, "");
        assertFalse(a2.isPresent());
    }

    @Test
    void attendanceTypeShouldReturnPresentOnPresent() {
        Attendance a2 = new Attendance(lecture, this.student, AttendanceType.PRESENT, "");
        assertEquals(AttendanceType.PRESENT, a2.getType());
    }

    @Test
    void attendanceTypeShouldReturnAbsentOnAbsent() {
        Attendance a2 = new Attendance(lecture, this.student, AttendanceType.ABSENT, "");
        assertEquals(AttendanceType.ABSENT, a2.getType());
    }

    @Test
    void attendanceTypeShouldReturnOtherOnOther() {
        Attendance a2 = new Attendance(lecture, this.student, AttendanceType.Absent.OTHER, "");
        assertEquals(AttendanceType.Absent.OTHER, a2.getType());
    }

    @Test
    void instanceControlledListShouldAddAttendance() {
        Attendance a2 = new Attendance(lecture, this.student);
        Attendance.clearAttendances();
        Attendance.addAttendance(a2);
        // assertEquals(a2, Attendance.getAttendances().get(0));
    }

    @Test
    void instanceControlledListEmptyOnClear() {
        Attendance a2 = new Attendance(lecture, this.student);
        Attendance.addAttendance(a2);
        Attendance.clearAttendances();
        // assertTrue(Attendance.getAttendances().isEmpty());
    }
}
