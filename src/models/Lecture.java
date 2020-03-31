package models;

import enums.SubjectType;
import models.user.Teacher;
import utils.Utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lecture {

    private static List<Lecture> lectures = new ArrayList<>();

    private LocalDateTime startDate;
    private int duration; // Duration in minutes
    private SubjectType subjectType;
    private Teacher teacher;
    private Class className;
    private List<Attendance> attendances;

    public Lecture(LocalDateTime startDate, int duration, SubjectType subjectType, Teacher teacher, Class className) {
        this(startDate, duration, subjectType, teacher, className, new ArrayList<>());
    }

    public Lecture(LocalDateTime startDate, int duration, SubjectType subjectType, Teacher teacher, Class className,
                   List<Attendance> attendances) {
        this.startDate = startDate;
        this.duration = duration;
        this.subjectType = subjectType;
        this.teacher = teacher;
        this.attendances = attendances;
        this.className = className;
    }

    public static void setLectures(ArrayList<Lecture> lectures) {
        Lecture.lectures = lectures;
    }

    public static void addLecture(Lecture lecture) throws IllegalArgumentException {
        if (isNewLectureValid(lecture))
            lectures.add(lecture);
    }

    private static boolean isNewLectureValid(Lecture lectureToCompare) throws IllegalArgumentException {
        for (Lecture lecture : lectures) {
            LocalDateTime max = lecture.startDate.plusMinutes(lecture.duration);
            LocalDateTime min = lecture.startDate;

            if (lecture.teacher.equals(lectureToCompare.teacher) &&
                    isWithinTimeRange(min, max, lectureToCompare.startDate)) {
                String pattern = "H:mm";
                throw new IllegalArgumentException(
                        String.format("Deze les kan niet tussen de tijd %s en %s gegeven worden",
                                Utils.formatDateTime(min, pattern),
                                Utils.formatDateTime(max, pattern)));
            }
        }

        return true;
    }

    private static boolean isWithinTimeRange(LocalDateTime min, LocalDateTime max, LocalDateTime toCompare) {
        // We want to check if the given time is between two values. If it is, it will return true.
        return (toCompare.isAfter(min) && toCompare.isBefore(max)) || toCompare.isEqual(min);
    }

    public static List<Lecture> getLectures() {
        return Collections.unmodifiableList(lectures);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }


    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public List<Attendance> getAttendances() {
        return Collections.unmodifiableList(attendances);
    }

    public void addAttendance(Attendance attendance) {
        this.attendances.add(attendance);
    }

    public void clearAttendances() {
        this.attendances.clear();
    }

    public void setAttendances(ArrayList<Attendance> attendances) {
        this.attendances = attendances;
    }

    @Override
    public String toString() {
        return String.format("De les %s begint om %s en duurt %d minuten",
                this.subjectType, Utils.formatDateTime(this.startDate, "H:mm"), this.duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return duration == lecture.duration &&
                Objects.equals(startDate, lecture.startDate) &&
                subjectType == lecture.subjectType;
    }
}
