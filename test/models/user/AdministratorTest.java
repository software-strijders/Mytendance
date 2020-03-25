package models.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

class AdministratorTest {

    private User s1;
    private User s2;
    private User s3;
    private User t1;
    private User t2;
    private User t3;

    @BeforeEach
    void initialize() {
        s1 = new Student("email1", "password1", "firstname1", "surname1", Utils.idGenerator());
        s2 = new Student("email1", "password1", "firstname1", "surname1", Utils.idGenerator());
        s3 = new Student("newEmail", "newPassword", "newFirstname", "newSurname", Utils.idGenerator());
        t1 = new Teacher("email1", "password1", "firstname1", "surname1", Utils.idGenerator());
        t2 = new Teacher("email1", "password1", "firstname1", "surname1", Utils.idGenerator());
        t3 = new Teacher("newEmail", "newPassword", "newFirstname", "newSurname", Utils.idGenerator());
    }

    @AfterEach
    void clearUserList() {
        User.getRegisteredUsers().clear();
    }

    @Test
    void shouldAddTeacher() {
        User.getRegisteredUsers().add(t1);
        assertTrue(User.getRegisteredUsers().contains(t1));
    }

    @Test
    void shouldAddStudent() {
        User.getRegisteredUsers().add(s1);
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
}
