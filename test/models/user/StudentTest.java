package models.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    private User student1;
    private User student2;
    private User student3;
    private User teacher1;
    private User teacher2;
    private User administrator1;
    private User administrator2;
    private Student testStudent;

    @BeforeEach
    void initialize() {
        student1 = new Student( "xander", "vedder", "foo@bar.noop", "123");
        student2 = new Student( "JoErI", "kOK", "foo@bar.noop", "123");
        student3 = new Student("ARJEN", "NORBART", "foo@bar.noop", "123");
        teacher1 = new Teacher("Arjen", "Norbart", "foo@bar.noop", "123");
        teacher2 = new Teacher("Milan", "Dol", "foo@bar.noop", "123");
        administrator1 = new Administrator("Ruben", "van de Brink", "foo@bar.noop", "123");
        administrator2 = new Administrator("Jort", "Willemsen", "foo@bar.noop", "123");
        testStudent = new Student("Test", "Studenter", "tester.studenter@student.hu.nl", "stupass");
        Administrator.addUser(testStudent);
    }

    @AfterEach
    public void clearAll() {
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

    @Test
    public void shouldRejectIdenticalStudentCredentials() {
        Student testStudentIdenticalCredentials = new Student("Test", "Studenter",
                "tester.studenter@student.hu.nl", "stupass");
        assertThrows(IllegalArgumentException.class, () -> Administrator.addUser(testStudentIdenticalCredentials));
    }

    @Test
    public void shouldAcceptIdenticalFirstName() {
        Student testStudentIdenticalFirstName = new Student("Test", "Stuud",
                "test.Stuud@student.hu.nl", "wachtwoord2");
        Administrator.addUser(testStudentIdenticalFirstName);
        assertDoesNotThrow(testStudentIdenticalFirstName::getFirstName);
    }

    @Test
    public void shouldAcceptIdenticalLastName() {
        Student testStudentIdenticalLastName = new Student("Teststudent", "studenter",
                "testers.studenters@student.hu.nl", "wachtwoooord");
        Administrator.addUser(testStudentIdenticalLastName);
        assertDoesNotThrow(testStudentIdenticalLastName::getLastName);
    }

    @Test
    public void shouldRejectIdenticalMail() {
        Student testStudentIdenticalMail = new Student("Teststudent2", "studenturus",
                "tester.studenter@student.hu.nl", "mijnwachtwoord");
        assertThrows(IllegalArgumentException.class, () -> Administrator.addUser(testStudentIdenticalMail));
    }

    @Test
    public void shouldAcceptIdenticalPassword() {
        Student testStudentIdenticalPassword = new Student("Teststudent3", "Studenturuski",
                "tester.studenturuski@student.hu.nl", "teletubieszijncool");
        Administrator.addUser(testStudentIdenticalPassword);
        assertDoesNotThrow(testStudentIdenticalPassword::getPassword);
    }

    @Test
    public void shouldRejectIdenticalUUID() {
        Student testStudentIdenticalUUID = new Student("Teststudent4", "Stundenterkerus",
                "tester.studenterkerus@student.hu.nl", "BierEnKaas", testStudent.userId);
        Administrator.addUser(testStudentIdenticalUUID);
        assertNotEquals(Student.getRegisteredStudents().get(0).userId,
                Student.getRegisteredStudents().get(Student.getRegisteredStudents().size() - 1).userId);
    }
}
