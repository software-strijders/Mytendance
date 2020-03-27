package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldOfStudyTest {

    @Test
    void shouldReturnLowercaseCodeInUppercase() {
        FieldOfStudy study = new FieldOfStudy("tict-sd");
        assertEquals("TICT-SD", study.toString());
    }

    @Test
    void shouldReturnUppercaseAndLowercaseInUppercase() {
        FieldOfStudy study = new FieldOfStudy("TiCt-Sd");
        assertEquals("TICT-SD", study.toString());
    }

    @Test
    void shouldReturnUppercaseWhenCodeIsUppercase() {
        FieldOfStudy study = new FieldOfStudy("TICT-SD");
        assertEquals("TICT-SD", study.toString());
    }
}
