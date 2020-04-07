package models;

import enums.SubjectType;
import models.user.Student;
import models.user.Teacher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class LectureTest {

    private LocalDateTime dateTime;
    private Teacher teacher;
    private Student student;
    private FieldOfStudy study;
    private Class theClass;
    private Lecture lecture;

    @BeforeEach
    void initialize() {
        this.dateTime = LocalDateTime.now().withHour(10).withMinute(30);
        this.teacher = new Teacher("Henk", "Tank", "Henk@hu.nl", "w8woord");
        this.student = new Student("Xander", "Vedder", "xander.vedder@student.hu.nl", "goeie");
        this.study = new FieldOfStudy("TICT-SD");
        this.theClass = new Class(1, 'A', this.study);
        this.theClass.addStudent(this.student);
        this.lecture = new Lecture(this.dateTime, 120, SubjectType.GP_SD, this.teacher, this.theClass);
        Lecture.addLecture(this.lecture);
    }

    @AfterEach
    void cleanUp() {
        Lecture.clearAllLectures();
    }

    @Test
    void toStringShouldBeEqual() {
        assertEquals("De les GP_SD begint om 10:30 en duurt 120 minuten", lecture.toString());
    }

    @Test
    void toStringShouldNotBeEqual() {
        assertNotEquals("De les GP_SD begint om 10:00 en duurt 120 minuten", lecture.toString());
    }

    @Test
    void lectureShouldBeEqual() {
        Lecture theSameLecture = new Lecture(this.dateTime, 120, SubjectType.GP_SD, this.teacher, this.theClass);
        assertEquals(this.lecture, theSameLecture);
    }

    @Test
    void lectureShouldNotBeEqual() {
        Lecture differentLecture = new Lecture(this.dateTime.plusDays(1), 120, SubjectType.GP_SD, this.teacher, this.theClass);
        assertNotEquals(this.lecture, differentLecture);
    }

    @Test
    void attendancesShouldBeAnUnmodifiableList() {
        assertThrows(UnsupportedOperationException.class, () ->
                this.lecture.getAttendances().add(new Attendance(this.student)));
    }

    @Test
    void shouldNotAddLectureWithinTimeRange() {
        Lecture lectureToCompare = new Lecture(LocalDateTime.now().withHour(11).withMinute(30),
                60, SubjectType.GP_SD, this.teacher, this.theClass);
        assertThrows(IllegalArgumentException.class, () -> Lecture.addLecture(lectureToCompare));
    }

    @Test
    void shouldNotAddLectureWithinTimeRangeLimit() {
        Lecture lectureToCompare = new Lecture(LocalDateTime.now().withHour(12).withMinute(29),
                60, SubjectType.GP_SD, this.teacher, this.theClass);
        assertThrows(IllegalArgumentException.class, () -> Lecture.addLecture(lectureToCompare));
    }

    @Test
    void shouldAddLectureOutsideTimeRange() {
        Lecture lectureToCompare = new Lecture(LocalDateTime.now().withHour(12).withMinute(30),
                60, SubjectType.GP_SD, this.teacher, this.theClass);
        assertDoesNotThrow(() -> Lecture.addLecture(lectureToCompare));
    }

    @Test
    void shouldAddLectureAtTheSameTimeWithADifferentTeacher() {
        Teacher differentTeacher = new Teacher("Hank", "Bank", "Hank@hu.nl", "w9woord");
        Lecture lectureWithDifferentTeacher = new Lecture(this.dateTime, 120, SubjectType.GP_SD, differentTeacher, this.theClass);
        assertDoesNotThrow(() -> Lecture.addLecture(lectureWithDifferentTeacher));
    }

    @Test
    void startDateShouldBeNullWhenInitializingNull() {
        Lecture l2 = new Lecture(null, 60, SubjectType.OOP, null, null);
        assertNull(l2.getStartDate());
    }

    @Test
    void attendanceListSizeShouldBeZero() {
        assertEquals(0, this.lecture.getAttendances().size());
    }

    @Test
    void attendanceListSizeShouldBeOne() {
        this.lecture.addAttendance(new Attendance(this.student));
        assertEquals(1, this.lecture.getAttendances().size());
    }

    @Test
    void attendanceListSizeShouldBeTwo() {
        this.lecture.addAttendance(new Attendance(this.student));
        this.lecture.addAttendance(new Attendance(this.student));
        assertEquals(2, this.lecture.getAttendances().size());
    }

    @Test
    void lectureStartTimeShouldBeEqual() {
        assertEquals(LocalTime.now().withHour(10).withMinute(30), this.lecture.getStartTime());
    }

    @Test
    void lectureStartTimeShouldBeEqualNextDay() {
        assertEquals(LocalDateTime.now().withHour(10).withMinute(30).plusDays(1).toLocalTime(),
                this.lecture.getStartTime());
    }

    @Test
    void lectureStartTimeShouldNotBeEqual() {
        assertEquals(LocalTime.now().withHour(10).withMinute(31), this.lecture.getStartTime());
    }

    @Test
    void lectureEndTimeEqualsStartTimeWithDuration() {
        assertEquals(this.lecture.getStartDate().plusMinutes(this.lecture.getDuration()).toLocalTime(),
                this.lecture.getEndTime());
    }

    @Test
    void classSizeFromLectureShouldBeEqual() {
        assertEquals(1, this.lecture.getClassSize());
    }

    @Test
    void classSizeFromLectureShouldNotBeEqual() {
        assertNotEquals(0, this.lecture.getClassSize());
    }
}
