package models;

import models.user.Student;
import utils.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Class {

    private static List<Class> allClasses = new ArrayList<>();

    private UUID classId;
    private int yearOfStudy;
    private char group;
    private FieldOfStudy studyField;
    private List<Student> students;

    public Class(UUID classId, int yearOfStudy, char group, FieldOfStudy studyField, List<Student> students) {
        this.classId = classId;
        this.yearOfStudy = yearOfStudy;
        this.group = group;
        this.studyField = studyField;
        this.students = students;
    }

    public String getName() {
        return Utils.formatClassName(this.studyField.toString(), this.yearOfStudy, this.group);
    }

    public static List<Class> getAllClasses() {
        return Collections.unmodifiableList(allClasses);
    }

    public static void addClass(Class newClass) {
        allClasses.add(newClass);
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof Class
                && this.yearOfStudy == ((Class)other).yearOfStudy
                && this.group == ((Class)other).group;
    }

    @Override
    public int hashCode() {
        return 0;            // what's going on here?
    }
}
