package models;

import models.user.Student;
import utils.Utils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Class {

    private static ArrayList<Class> allClasses = new ArrayList<>();

    private UUID classId;
    private int yearOfStudy;
    private char group;

    private FieldOfStudy studyField;
    private ArrayList<Student> students;

    public Class(UUID classId, int yearOfStudy, char group, FieldOfStudy studyField, ArrayList<Student> students) {
        this.classId = classId;
        this.yearOfStudy = yearOfStudy;
        this.group = group;
        this.studyField = studyField;
        this.students = students;
    }

    public String getName() {
        return Utils.formatClassName(this.studyField.toString(), this.yearOfStudy, this.group);
    }

    public static ArrayList<Class> getAllClasses() {
        return allClasses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return yearOfStudy == aClass.yearOfStudy &&
                group == aClass.group;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
