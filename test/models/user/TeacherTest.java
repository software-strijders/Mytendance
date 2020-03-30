package models.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {
    private Teacher test;

    @BeforeEach
    public void init() {
        test = new Teacher("VoornaamTest", "AchternaamTest", "testDocent@hu.nl",
                "test");
        Administrator.addUser(test);
    }

    @AfterEach
    public void clearAll() {
        User.clearUsers();
    }

    @Test
    void shouldMatchTeacherFullName() {
        assertEquals("VoornaamTest AchternaamTest", test.toString());
    }

    @Test
    void shouldRejectIdenticalTeacherCredentials() {
        Teacher identical = new Teacher("VoornaamTest", "AchternaamTest", "testDocent@hu.nl",
                "test");
        assertThrows(IllegalArgumentException.class, () -> Administrator.addUser(identical));
    }

    @Test
    void shouldRejectIdenticalEmail() {
        Teacher sameEmail = new Teacher("joost", "mag", "testDocent@hu.nl", "weten");
        assertThrows(IllegalArgumentException.class, () -> Administrator.addUser(sameEmail));
    }

    @Test
    void shouldAcceptIdenticalFirstName() {
        Teacher sameFirstName = new Teacher("VoornaamTest", "twee", "joost@hu.nl",
                "nieuwe");
        Administrator.addUser(sameFirstName);
        assertDoesNotThrow(sameFirstName::getFirstName);
    }

    @Test
    void shouldAcceptIdenticalLastName() {
        Teacher sameLastName = new Teacher("henk", "AchternaamTest", "henk@hu.nl",
                "passw");
        Administrator.addUser(sameLastName);
        assertDoesNotThrow(sameLastName::getLastName);
    }

    @Test
    void shouldAcceptIdenticalPassword() {
        Teacher samePassword = new Teacher("Milan", "Dol", "MilanD@hu.nl", "test");
        Administrator.addUser(samePassword);
        assertDoesNotThrow(samePassword::getPassword);
    }

    @Test
    void shouldRejectIdenticalUUID() {
        Teacher sameUUID = new Teacher("Nelleke", "Post", "nellie@hu.nl",
                "mijnsterkewachtwoorod", Teacher.getRegisteredUsers().get(0).userId);
        Administrator.addUser(sameUUID);
//        System.out.println(Teacher.getRegisteredTeachers().get(0).toString());
//        System.out.println(Teacher.getRegisteredTeachers().get(0).userId);
//        System.out.println(Teacher.getRegisteredTeachers().get(Teacher.getRegisteredTeachers().size()-1).toString());
//        System.out.println(Teacher.getRegisteredTeachers().get(Teacher.getRegisteredTeachers().size()-1).userId);
        assertNotEquals(Teacher.getRegisteredTeachers().get(0).userId,
                Teacher.getRegisteredTeachers().get(Teacher.getRegisteredTeachers().size() - 1).userId);
    }
}