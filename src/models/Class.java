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
    private ArrayList<Lecture> allLectures = new ArrayList<>();
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

    public static void setAllClasses(List<Class> allClasses) {
        Class.allClasses = allClasses;
    }

    public UUID getClassId() {
        return classId;
    }

    public void setClassId(UUID classId) {
        this.classId = classId;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public char getGroup() {
        return group;
    }

    public void setGroup(char group) {
        this.group = group;
    }

    public FieldOfStudy getStudyField() {
        return studyField;
    }

    public void setStudyField(FieldOfStudy studyField) {
        this.studyField = studyField;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public ArrayList<Lecture> getAllLectures() {
        return this.allLectures;
    }

    public static List<Class> getAllClasses() {
        return allClasses;
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
    public String toString() {
        return this.getName();
    }
}
