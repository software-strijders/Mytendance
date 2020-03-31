package models;

import enums.AttendanceType;
import enums.ReasonType;
import enums.SubjectType;
import models.user.Student;
import models.user.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LectureTest {

    private LocalDateTime d1;
    private Student s1;
    private Attendance a1;
    private Lecture l1;
    private ArrayList<Attendance> emptyArrayList;
    private FieldOfStudy f1 = new FieldOfStudy("XXX");
    private Teacher t1 = new Teacher( "x", "y", "z", "x");
    private Class c1 = new Class(Utils.idGenerator(),1, 'A', f1);

    @BeforeEach
    void beforeEachTest() {
        d1 = LocalDate.now().atStartOfDay();
        s1 = new Student("firstname", "lastname", "email", "password");
        a1 = new Attendance(ReasonType.ENTOMBMENT, "begraven", s1, AttendanceType.ABSENT);
        l1 = new Lecture(d1, 60, SubjectType.OOP, t1, c1);
        emptyArrayList = new ArrayList<>();
    }

    @Test
    void testReturnValueOfGetStartDate() {
        assertEquals(d1, l1.getStartDate());
    }

    @Test
    void getStartDateShouldReturnNullWhenNull() {
        Lecture l2 = new Lecture(null, 60, SubjectType.OOP, null, null);
        assertNull(l2.getStartDate());
    }

    @Test
    void testReturnValueOfGetAllAttendances() {
        l1.addAttendance(a1);
        ArrayList<Attendance> list1 = new ArrayList<>();
        list1.add(a1);
        assertEquals(list1, l1.getAttendances());
    }

    @Test
    void getAllAttendancesShouldReturnEmptyArrayListWhenEmpty() {
        assertEquals(emptyArrayList, l1.getAttendances());
    }
}
