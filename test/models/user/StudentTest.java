package models.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private User student1;
    private User student2;
    private User student3;
    private User teacher1;
    private User teacher2;
    private User administrator1;
    private User administrator2;

    @BeforeEach
    void initialize() {
        student1 = new Student( "xander", "vedder", "foo@bar.noop", "123");
        student2 = new Student( "JoErI", "kOK", "foo@bar.noop", "123");
        student3 = new Student("ARJEN", "NORBART", "foo@bar.noop", "123");
        teacher1 = new Teacher("Arjen", "Norbart", "foo@bar.noop", "123");
        teacher2 = new Teacher("Milan", "Dol", "foo@bar.noop", "123");
        administrator1 = new Administrator("Ruben", "van de Brink", "foo@bar.noop", "123");
        administrator2 = new Administrator("Jort", "Willemsen", "foo@bar.noop", "123");

        User.clearUsers();
    }

    @Test
    void shouldFormatNameCorrectlyWithLowerCaseNames() {
        assertEquals("Xander Vedder", student1.toString());
    }

    @Test
    void shouldFormatNameCorrectlyWithMixedCaseNames() {
        assertEquals("Joeri Kok", student2.toString());
    }

    @Test
    void shouldFormatNameCorrectlyWithCapitalizedNames() {
        assertEquals("Arjen Norbart", student3.toString());
    }

    @Test
    void shouldGiveAllStudentsWhenAddingStudents() {
        User.addUser(student1);
        User.addUser(student2);
        assertEquals(2, Student.getRegisteredStudents().size());
    }

    @Test
    void shouldGiveNoStudentsWhenAddingTeachers() {
        User.addUser(teacher1);
        User.addUser(teacher2);
        assertEquals(0, Student.getRegisteredStudents().size());
    }

    @Test
    void shouldgiveNoStudentsWhenAddingAdministrators() {
        User.addUser(administrator1);
        User.addUser(administrator2);
        assertEquals(0, Student.getRegisteredStudents().size());
    }

    @Test
    void shouldGiveTheRightAmountOfStudentsWhenAddingStudentsAndTeachers() {
        User.addUser(student1);
        User.addUser(teacher1);
        assertEquals(1, Student.getRegisteredStudents().size());
    }

    @Test
    void shouldgiveTheRightAmountOfStudentsWhenAddingStudentsAndAdministrators() {
        User.addUser(student1);
        User.addUser(administrator1);
        assertEquals(1, Student.getRegisteredStudents().size());
    }
}
