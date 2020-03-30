package models;

import enums.AttendanceType;
import enums.ReasonType;
import enums.SubjectType;
import models.user.Student;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LectureTest {

    LocalDate d1 = LocalDate.now();
    LocalDate d2 = LocalDate.now().plusDays(1);
    Student s1 = new Student("firstname", "lastname", "email", "password");
    Attendance a1 = new Attendance(ReasonType.ENTOMBMENT, "begraven", s1, AttendanceType.ABSENT);
    Lecture l1 = new Lecture(d1, 60, SubjectType.OOP);
    @Test
    void testGetStartDate(){
        assertEquals(d1, l1.getStartDate());
    }

    @Test
    void testGetStartDateWhenNull(){
        Lecture l2 = new Lecture(null, 60, SubjectType.OOP);
        assertNull(l2.getStartDate());
    }

    @Test
    void testGetAllAttendances(){
        l1.getAllAttendances().add(a1);
        ArrayList<Attendance> list1 = new ArrayList<Attendance>();
        list1.add(a1);
        assertEquals(list1, l1.getAllAttendances());
    }
}
