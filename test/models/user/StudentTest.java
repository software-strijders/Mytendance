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
        testStudent = new Student("Test", "Studenter", "tester.studenter@student.hu.nl", "stupass");
        teacher1 = new Teacher("Arjen", "Norbart", "foo@bar.noop", "123");
        teacher2 = new Teacher("Milan", "Dol", "foo@bar.noop", "123");
        administrator1 = new Administrator("Ruben", "van de Brink", "foo@bar.noop", "123");
        administrator2 = new Administrator("Jort", "Willemsen", "foo@bar.noop", "123");
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
    void shouldIncrementRegisteredStudentsOnAddStudentDifferentEmail() {
        User.addUser(student1);
        User.addUser(student2);
        assertEquals(2, Student.getRegisteredStudents().size());
    }

    @Test
    void shouldN0tIncrementRegisteredStudentsOnAddTeacher() {
        User.addUser(teacher1);
        User.addUser(teacher2);
        assertEquals(0, Student.getRegisteredStudents().size());
    }

    @Test
    void shouldN0tIncrementRegisteredStudentsOnAddAdmin() {
        User.addUser(administrator1);
        User.addUser(administrator2);
        assertEquals(0, Student.getRegisteredStudents().size());
    }

    @Test
    public void shouldRejectAddStudentIdenticalStudentCredentials() {
        Student testStudentIdenticalCredentials = new Student("Test", "Studenter",
                "tester.studenter@student.hu.nl", "stupass");
        User.addUser(testStudent);
        assertThrows(IllegalArgumentException.class, () -> Administrator.addUser(testStudentIdenticalCredentials));
    }

    @Test
    public void shouldAcceptAddStudentIdenticalFirstName() {
        Student testStudentIdenticalFirstName = new Student("Test", "Stuud",
                "test.Stuud@student.hu.nl", "wachtwoord2");
        assertDoesNotThrow(() -> User.addUser(testStudentIdenticalFirstName));
    }

    @Test
    public void shouldAcceptAddStudentIdenticalLastName() {
        Student testStudentIdenticalLastName = new Student("Teststudent", "studenter",
                "testers.studenters@student.hu.nl", "wachtwoooord");
        assertDoesNotThrow(() -> User.addUser(testStudentIdenticalLastName));
    }

    @Test
    public void shouldRejectAddStudentIdenticalMail() {
        Student testStudentIdenticalMail = new Student("Teststudent2", "studenturus",
                "tester.studenter@student.hu.nl", "mijnwachtwoord");
        User.addUser(testStudent);
        assertThrows(IllegalArgumentException.class, () -> Administrator.addUser(testStudentIdenticalMail));
    }

    @Test
    public void shouldAcceptAddStudentIdenticalPassword() {
        Student testStudentIdenticalPassword = new Student("Teststudent3", "Studenturuski",
                "tester.studenturuski@student.hu.nl", "teletubieszijncool");
        Administrator.addUser(testStudent);
        assertDoesNotThrow(() -> User.addUser(testStudentIdenticalPassword));
    }

    @Test
    public void shouldRejectAddStudentIdenticalUUID() {
        Student testStudentIdenticalUUID = new Student("Teststudent4", "Stundenterkerus",
                "tester.studenterkerus@student.hu.nl", "BierEnKaas", testStudent.userId);
        Administrator.addUser(testStudentIdenticalUUID);
        assertNotEquals(Student.getRegisteredStudents().get(0).userId,
                Student.getRegisteredStudents().get(Student.getRegisteredStudents().size() - 1).userId);
    }
}
