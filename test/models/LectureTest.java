package models;

import enums.AttendanceType;
import enums.ReasonType;
import enums.SubjectType;
import models.user.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LectureTest {

    LocalDate d1;
    Student s1;
    Attendance a1;
    Lecture l1;
    ArrayList<Attendance> emptyArrayList;

    @BeforeEach
    void beforeEachTest() {
        d1 = LocalDate.now();
        s1 = new Student("firstname", "lastname", "email", "password");
        a1 = new Attendance(ReasonType.ENTOMBMENT, "begraven", s1, AttendanceType.ABSENT);
        l1 = new Lecture(d1, 60, SubjectType.OOP);
        emptyArrayList = new ArrayList<>();
    }

    @Test
    void testReturnValueOfGetStartDate() {
        assertEquals(d1, l1.getStartDate());
    }

    @Test
    void getStartDateShouldReturnNullWhenNull() {
        Lecture l2 = new Lecture(null, 60, SubjectType.OOP);
        assertNull(l2.getStartDate());
    }

    @Test
    void testReturnValueOfGetAllAttendances() {
        l1.getAllAttendances().add(a1);
        ArrayList<Attendance> list1 = new ArrayList<Attendance>();
        list1.add(a1);
        assertEquals(list1, l1.getAllAttendances());
    }

    @Test
    void getAllAttendancesShouldReturnEmptyArrayListWhenEmpty() {
        assertEquals(emptyArrayList, l1.getAllAttendances());
    }
}
