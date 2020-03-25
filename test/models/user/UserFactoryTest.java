package models.user;

import enums.UserType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    void shouldCreateStudent() {
        User newUser = UserFactory.create("email1", "password1", "firstname1", "surname1", UserType.STUDENT);
        assertTrue(newUser instanceof Student);
    }

    @Test
    void shouldCreateTeacher() {
        User newUser = UserFactory.create("email1", "password1", "firstname1", "surname1", UserType.TEACHER);
        assertTrue(newUser instanceof Teacher);
    }
}
