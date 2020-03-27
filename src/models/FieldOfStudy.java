package models;

import java.util.ArrayList;

public class FieldOfStudy {

    private static ArrayList<FieldOfStudy> studyFields = new ArrayList<>();

    private String code;
    private String description;

    public FieldOfStudy(String code) {
        this(code, "");
    }

    public FieldOfStudy(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static ArrayList<FieldOfStudy> getFieldOfStudies() {
        return studyFields;
    }

    public String toString() {
        return code.toUpperCase();
    }
}
