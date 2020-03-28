package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FieldOfStudy {

    private static List<FieldOfStudy> studyFields = new ArrayList<>();

    private String code;
    private String description;

    public FieldOfStudy(String code) {
        this(code, "");
    }

    public FieldOfStudy(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static List<FieldOfStudy> getFieldOfStudies() {
        return Collections.unmodifiableList(studyFields);
    }

    public static void addStudyField(FieldOfStudy studyField) {
        studyFields.add(studyField);
    }

    public String toString() {
        return this.code.toUpperCase();
    }
}
