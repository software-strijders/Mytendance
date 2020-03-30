package models;

import enums.SubjectType;

import java.time.LocalDate;
import java.util.ArrayList;

public class Lecture {
    private LocalDate startDate;
    private int duration;
    private SubjectType subjectType;

    private ArrayList<Class> className = new ArrayList<>();
    private ArrayList<Attendance> allAttendances = new ArrayList<>();

    public Lecture(LocalDate startDate, int duration, SubjectType subjectType) {
        this.startDate = startDate;
        this.duration = duration;
        this.subjectType = subjectType;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public ArrayList<Attendance> getAllAttendances() {
        return allAttendances;
    }

    @Override
    public String toString() {
        return String.format("%s duurt %d minuten", this.subjectType, this.duration);
    }
}
