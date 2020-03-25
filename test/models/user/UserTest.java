package models.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
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

    @Test
    void shouldBeEqualsTeacher() {
        assertEquals(t1, t2);
    }

    @Test
    void shouldBeEqualsStudent() {
        assertEquals(s1, s2);
    }

    @Test
    void shouldNotBeEqualsTeacher() {
        assertNotEquals(t1, t3);
    }

    @Test
    void shouldNotBeEqualsStudent() {
        assertNotEquals(s1, s3);
    }
}
