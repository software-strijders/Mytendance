package models.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {

    private User s1;
    private User s2;
    private User s3;
    private User s4;
    private User t1;
    private User t2;
    private User t3;
    private User t4;

    @BeforeEach
    void initialize() {
        s1 = new Student("Semail1", "Spassword1", "Sfirstname1", "Ssurname1", Utils.idGenerator());
        s2 = new Student("Semail1", "Spassword1", "Sfirstname1", "Ssurname1", Utils.idGenerator());
        s3 = new Student("SnewEmail", "SnewPassword", "SnewFirstname", "SnewSurname", Utils.idGenerator());
        s4 = new Student("SnewEmail2", "SnewPassword2", "SnewFirstname", "SnewSurname", Utils.idGenerator());
        t1 = new Teacher("Temail1", "Tpassword1", "Tfirstname1", "Tsurname1", Utils.idGenerator());
        t2 = new Teacher("Temail1", "Tpassword1", "Tfirstname1", "Tsurname1", Utils.idGenerator());
        t3 = new Teacher("TnewEmail", "TnewPassword", "TnewFirstname", "TnewSurname", Utils.idGenerator());
        t4 = new Teacher("TnewEmail2", "TnewPassword2", "TnewFirstname", "TnewSurname", Utils.idGenerator());
    }

    @AfterEach
    void clearUserList() {
        User.getRegisteredUsers().clear();
    }

    @Test
    void shouldAddTeacher() {
        Administrator.addUser(t1);
        assertTrue(User.getRegisteredUsers().contains(t1));
    }

    @Test
    void shouldAddStudent() {
        Administrator.addUser(s1);
        assertTrue(User.getRegisteredUsers().contains(s1));
    }

    @Test
    void shouldThrowExceptionWhenDuplicateStudentsAreAdded() {
        Administrator.addUser(s1);
        assertThrows(IllegalArgumentException.class, () -> Administrator.addUser(s2));
    }

    @Test
    void shouldThrowExceptionWhenDuplicateTeachersAreAdded() {
        Administrator.addUser(t1);
        assertThrows(IllegalArgumentException.class, () -> Administrator.addUser(t2));
    }

    @Test
    void shouldNotThrowExceptionWhenDuplicateStudentsAreAdded() {
        Administrator.addUser(s1);
        assertDoesNotThrow(() -> Administrator.addUser(s3));
    }

    @Test
    void shouldNotThrowExceptionWhenDuplicateTeachersAreAdded() {
        Administrator.addUser(t1);
        assertDoesNotThrow(() -> Administrator.addUser(t3));
    }

    @Test
    void shouldNotThrowExceptionWhenMultipleTeachersAreAdded() {
        Administrator.addUser(t1);
        Administrator.addUser(t3);
        assertDoesNotThrow(() -> Administrator.addUser(t4));
    }

    @Test
    void shouldNotThrowExceptionWhenMultipleStudentsAreAdded() {
        Administrator.addUser(s1);
        Administrator.addUser(s3);
        assertDoesNotThrow(() -> Administrator.addUser(s4));
    }

    @Test
    void shouldNotThrowExceptionWhenMultipleUsersAreAdded() { // Multiple Users = Students and Teachers combined
        Administrator.addUser(s1);
        Administrator.addUser(t1);
        Administrator.addUser(s3);
        Administrator.addUser(t3);
        Administrator.addUser(t4);
        assertDoesNotThrow(() -> Administrator.addUser(s4));
    }
}
