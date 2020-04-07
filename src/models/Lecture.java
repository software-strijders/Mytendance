package models;

import enums.SubjectType;
import models.user.Teacher;
import utils.Utils;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Lecture {

    private static List<Lecture> allLectures = new ArrayList<>();

    private LocalDateTime startDate;
    private int duration; // Duration in minutes
    private SubjectType subject;
    private Teacher teacher;
    private Class className;
    private List<Attendance> attendances;

    public Lecture(LocalDateTime startDate, int duration, SubjectType subject, Teacher teacher, Class className) {
        this(startDate, duration, subject, teacher, className, new ArrayList<>());
    }

    public Lecture(LocalDateTime startDate, int duration, SubjectType subject, Teacher teacher, Class className,
                   List<Attendance> attendances) {
        this.startDate = startDate;
        this.duration = duration;
        this.subject = subject;
        this.teacher = teacher;
        this.attendances = attendances;
        this.className = className;
    }

    public static void clearAllLectures() {
        allLectures.clear();
    }

    public static void addLecture(Lecture lecture) throws IllegalArgumentException {
        if (isNewLectureValid(lecture))
            allLectures.add(lecture);
    }

    private static boolean isNewLectureValid(Lecture lectureToCompare) throws IllegalArgumentException {
        for (Lecture lecture : allLectures) {
            LocalDateTime max = lecture.startDate.plusMinutes(lecture.duration);
            LocalDateTime min = lecture.startDate;

            if (lecture.teacher.equals(lectureToCompare.teacher) &&
                    isWithinTimeRange(min, max, lectureToCompare.startDate)) {
                String pattern = "H:mm";
                throw new IllegalArgumentException(
                        String.format("Deze les kan niet tussen %s en %s uur gegeven worden",
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

    public static List<Lecture> getAllLectures() {
        return Collections.unmodifiableList(allLectures);
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    // Required by the TableView of AttendanceOverviewController
    public LocalTime getStartTime() {
        return this.startDate.toLocalTime();
    }

    // Required by the TableView of AttendanceOverviewController
    public LocalTime getEndTime() {
        return this.startDate.plusMinutes(this.duration).toLocalTime();
    }

    // Required by the TableView of AttendanceOverviewController
    public int getClassSize() {
        return this.className.getSize();
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public SubjectType getSubject() {
        return this.subject;
    }

    public void setSubject(SubjectType subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Class getClassName() {
        return this.className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public int getAttendaceSize() {
        return this.attendances.size();
    }

    public List<Attendance> getAttendances() {
        return Collections.unmodifiableList(this.attendances);
    }

    public void addAttendance(Attendance attendance) {
        this.attendances.add(attendance);
    }

    public void removeAttendance(Attendance attendance) {
        this.attendances.remove(attendance);
    }

    public void clearAttendances() {
        this.attendances.clear();
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    @Override
    public String toString() {
        return String.format("De les %s begint om %s en duurt %d minuten",
                this.subject, Utils.formatDateTime(this.startDate, "H:mm"), this.duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return duration == lecture.duration &&
                Objects.equals(startDate, lecture.startDate) &&
                subject == lecture.subject;
    }
}
