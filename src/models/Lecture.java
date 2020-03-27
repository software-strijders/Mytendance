package models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Lecture {
    private LocalDate startDate;
    private LocalTime duration;
    private ArrayList<Attendance> attendances = new ArrayList<Attendance>();
    private ArrayList<Class> className = new ArrayList<Class>();

    public Lecture(LocalDate startDate, LocalTime duration) {
        this.startDate = startDate;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Les" + startDate + " duurt " + duration;
    }
}