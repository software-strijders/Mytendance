package models;

import models.user.Student;
import utils.Utils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Class {

    private static List<Class> allClasses = new ArrayList<>();

    private UUID classId;
    private int yearOfStudy;
    private char group;

    private FieldOfStudy studyField;
    private ArrayList<Lecture> lectures = new ArrayList<>();
    private List<Student> students;

    public Class(int yearOfStudy, char group, FieldOfStudy studyField) {
        this(yearOfStudy, group, studyField, new ArrayList<>());
    }

    public Class(int yearOfStudy, char group, FieldOfStudy studyField, List<Student> students) {
        this.classId = Utils.idGenerator();
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
        return this.classId;
    }

    public void setClassId(UUID classId) {
        this.classId = classId;
    }

    public int getYearOfStudy() {
        return this.yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    public char getGroup() {
        return this.group;
    }

    public void setGroup(char group) {
        this.group = group;
    }

    public FieldOfStudy getStudyField() {
        return this.studyField;
    }

    public void setStudyField(FieldOfStudy studyField) {
        this.studyField = studyField;
    }

    public int getSize() {
        return this.students.size();
    }

    public static List<Class> getAllClasses() {
        return Collections.unmodifiableList(allClasses);
    }

    public static void addClass(Class newClass) {
        allClasses.add(newClass);
    }

    public List<Lecture> getLectures() {
        return Collections.unmodifiableList(this.lectures);
    }

    public List<Lecture> getLecturesByDate(LocalDate date) {
        return this.lectures.stream().filter(lecture ->
                lecture.getStartDate().toLocalDate().isEqual(date)).collect(Collectors.toList());
    }

    public void setLectures(ArrayList<Lecture> lectures) {
        this.lectures = lectures;
    }

    public void addLecture(Lecture lecture) {
        this.lectures.add(lecture);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(this.students);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
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
