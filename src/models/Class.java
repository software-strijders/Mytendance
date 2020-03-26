package models;

import models.user.Student;

import java.util.ArrayList;
import java.util.UUID;

public class Class {

    private static ArrayList<Class> allClasses = new ArrayList<>();

    private UUID classId;
    private int yearOfStudy;
    private char group;
    private String name;

    private FieldOfStudy studyField;
    private ArrayList<Student> students = new ArrayList<>();

    public Class(UUID classId, int yearOfStudy, char group, String name, FieldOfStudy studyField, ArrayList<Student> students) {
        this.classId = classId;
        this.yearOfStudy = yearOfStudy;
        this.group = group;
        this.name = name;
        this.studyField = studyField;
        this.students = students;
    }
    public static ArrayList<Class> getAllClasses() {
        return allClasses;
    }
}
