package models.data;

import enums.AttendanceType;
import enums.SubjectType;
import models.Attendance;
import models.Class;
import models.FieldOfStudy;
import models.Lecture;
import models.user.Administrator;
import models.user.Student;
import models.user.Teacher;
import models.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class VolatileData {

    /**
     * We should not be able to make an instance of this class
     */
    private VolatileData() {}

    /**
     * This method is used to create all necessary data needed for this application.
     */
    public static void createData() {
        createUsers();
        createClasses();
        createLectures();
    }

    private static void createUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(createStudents());
        users.addAll(createTeachers());
        users.addAll(createAdministrators());

        for (User user : users)
            Administrator.addUser(user);
    }

    private static List<User> createStudents() {
        List<User> students = new ArrayList<>();
        students.add(new Student("Arjen", "Norbart", "arjen.norbart@student.hu.nl", "password"));
        students.add(new Student("Jort", "Willemsen", "jort.willemsen@student.hu.nl", "p@ssword"));
        students.add(new Student("Milan", "Dol", "milan.dol@student.hu.nl", "m1lan"));
        students.add(new Student("Xander", "Vedder", "xander.vedder@student.hu.nl", "sander"));
        students.add(new Student("Ruben", "van den Brink", "ruben.brink@student.hu.nl", "rururuben"));
        students.add(new Student("Joeri", "Kok", "joeri.j.kok@student.hu.nl", "c++"));
        students.add(new Student("Bart", "Velthuizen", "bart.velthuizen@student.hu.nl", "1234"));
        students.add(new Student("Fatih", "Çan", "fatih.can@student.hu.nl", "1234"));
        students.add(new Student("Ibrahim", "Aldarra", "ibrahim.aldarra@student.hu.nl", "1234"));
        students.add(new Student("Marlon", "Scholten", "marlon.scholten@student.hu.nl", "1234"));
        students.add(new Student("Max", "Bosch", "max.bosch@student.hu.nl", "1234"));
        students.add(new Student("Nickey", "Stoof", "nickey.stoof@student.hu.nl", "1234"));
        students.add(new Student("Ruben", "van Dijk", "ruben.v.dijk@student.hu.nl", "1234"));
        students.add(new Student("Etiënne", "Hendrikse", "etienne.hendrikse@student.hu.nl", "1234"));
        students.add(new Student("Gerson", "Mak", "gerson.mak@student.hu.nl", "1234"));
        students.add(new Student("Gianni", "Giard", "gianni.giard@student.hu.nl", "1234"));
        students.add(new Student("Kelly", "van den Bosch", "kelly.v.d.bosch@student.hu.nl", "1234"));
        students.add(new Student("Lex", "Comes", "lex.comes@student.hu.nl", "1234"));
        students.add(new Student("Tim", "Zegeling", "tim.zegeling@student.hu.nl", "1234"));
        students.add(new Student("Tygo", "Steenbergen", "tygo.steenbergen@student.hu.nl", "1234"));
        students.add(new Student("Batuhan", "Özcan", "batuhan.ozcan@student.hu.nl", "1234"));
        students.add(new Student("Daniël", "Lankheet", "daniel.lankheet@student.hu.nl", "1234"));
        students.add(new Student("Fars", "Alkhadrawy", "fars.alkhadrawy@student.hu.nl", "1234"));
        students.add(new Student("Faycal", "el Moussati", "@student.hu.nl", "1234"));
        students.add(new Student("Gabriël", "Farhat", "gabriel.farhat@student.hu.nl", "1234"));
        students.add(new Student("Nick", "de Gooijer", "nick.d.gooijer@student.hu.nl", "1234"));
        students.add(new Student("Twan", "Gijselhart", "twan.gijselhart@student.hu.nl", "1234"));

        return students;
    }

    private static List<User> createTeachers() {
        List<User> teachers = new ArrayList<>();
        teachers.add(new Teacher("Henk", "Tank", "Henk@hu.nl", "w8woord"));
        teachers.add(new Teacher("Brian", "van der Bijl", "briand.vd.bijl@hu.nl", "1234"));
        teachers.add(new Teacher("Dietske", "Obbink", "dietske.obbink@hu.nl", "1234"));
        teachers.add(new Teacher("Rik", "Boss", "rik.boss@hu.nl", "1234"));
        teachers.add(new Teacher("Roelant", "Ossewaarde", "roelant.ossewaarde@hu.nl", "1234"));

        return teachers;
    }

    private static List<User> createAdministrators() {
        List<User> administrators = new ArrayList<>();
        administrators.add(new Administrator("Jos", "van Reenen", "jos.v.reenen@admin.hu.nl", "1234"));
        administrators.add(new Administrator("André", "Donk", "andre.donk@admin.hu.nl", "1234"));
        administrators.add(new Administrator("Peter", "van Rooijen", "peter.v.rooijen@admin.hu.nl", "1234"));

        return administrators;
    }

    private static void createClasses() {
        FieldOfStudy fieldOfStudy = createFieldOfStudies();
        List<Class> classes = new ArrayList<>();

        // For now add all students to all classes, this should change obviously
        classes.add(new Class(1, 'A', fieldOfStudy, Student.getRegisteredStudents()));
        classes.add(new Class(1, 'B', fieldOfStudy, Student.getRegisteredStudents()));
        classes.add(new Class(1, 'C', fieldOfStudy, Student.getRegisteredStudents()));
        classes.add(new Class(1, 'D', fieldOfStudy, Student.getRegisteredStudents()));
        classes.add(new Class(1, 'E', fieldOfStudy, Student.getRegisteredStudents()));

        for (Class theClass : classes)
            Class.addClass(theClass);
    }

    private static FieldOfStudy createFieldOfStudies() {
        List<FieldOfStudy> fieldOfStudies = new ArrayList<>();
        fieldOfStudies.add(new FieldOfStudy("TICT-SD"));
        fieldOfStudies.add(new FieldOfStudy("TICT-TI"));
        fieldOfStudies.add(new FieldOfStudy("TICT-AI"));
        fieldOfStudies.add(new FieldOfStudy("TICT-CSC"));
        fieldOfStudies.add(new FieldOfStudy("TICT-BIM"));

        for (FieldOfStudy studyField : fieldOfStudies)
            FieldOfStudy.addStudyField(studyField);

        return fieldOfStudies.get(0); // We need the first one to create the classes (most relevant)
    }


    private static void createLectures() {
        LocalDateTime now = LocalDate.now().atStartOfDay();
        List<Teacher> teachers = Teacher.getRegisteredTeachers();
        List<Class> classes = Class.getAllClasses();

        Class v1d = classes.get(3);
        Class v1e = classes.get(4);
        Teacher henk = teachers.get(0);
        Teacher roelant = teachers.get(4);

        List<Lecture> lectures = new ArrayList<>();
        lectures.add(new Lecture(now.withHour(10).withMinute(0), 60, SubjectType.OOAD, henk, v1d, createAttendances(v1d.getStudents())));
        v1d.addLecture(lectures.get(0));
        henk.addLecture(lectures.get(0));
        lectures.add(new Lecture(now.withHour(11).withMinute(0), 60, SubjectType.OOAD, henk, v1d, createAttendances(v1d.getStudents())));
        v1d.addLecture(lectures.get(1));
        henk.addLecture(lectures.get(1));
        lectures.add(new Lecture(now.withHour(12).withMinute(0), 60, SubjectType.GP_SD, henk, v1d, createAttendances(v1d.getStudents())));
        v1d.addLecture(lectures.get(2));
        henk.addLecture(lectures.get(2));
        lectures.add(new Lecture(now.withHour(13).withMinute(0), 60, SubjectType.GP_SD, henk, v1d, createAttendances(v1d.getStudents())));
        v1d.addLecture(lectures.get(3));
        henk.addLecture(lectures.get(3));

        henk.addClass(v1d);

        lectures.add(new Lecture(now.withHour(10).withMinute(0), 60, SubjectType.GP_SD, roelant, v1e, createAttendances(v1e.getStudents())));
        v1e.addLecture(lectures.get(4));
        roelant.addLecture(lectures.get(4));
        lectures.add(new Lecture(now.withHour(11).withMinute(0), 60, SubjectType.GP_SD, roelant, v1e, createAttendances(v1e.getStudents())));
        v1e.addLecture(lectures.get(5));
        roelant.addLecture(lectures.get(5));
        lectures.add(new Lecture(now.withHour(12).withMinute(0), 60, SubjectType.OOP, roelant, v1e, createAttendances(v1e.getStudents())));
        v1e.addLecture(lectures.get(6));
        roelant.addLecture(lectures.get(6));
        lectures.add(new Lecture(now.withHour(13).withMinute(0), 60, SubjectType.OOP, roelant, v1e, createAttendances(v1e.getStudents())));
        v1e.addLecture(lectures.get(7));
        roelant.addLecture(lectures.get(7));

        roelant.addClass(v1e);

        for (Lecture lecture : lectures)
            Lecture.addLecture(lecture);
    }

    private static List<Attendance> createAttendances(List<Student> students) {
        List<Attendance> attendances = new ArrayList<>();
        Random random = new Random();

        for (Student student : students) {
            Attendance attendance = new Attendance(student, random.nextDouble() < 0.25
                    ? AttendanceType.Absent.values()[random.nextInt(AttendanceType.Absent.values().length)]
                    : AttendanceType.PRESENT, ""); // Descriptions are left empty on absent attendances
            attendances.add(attendance);
            Attendance.addAttendance(attendance);
        }
        return attendances;
    }
}
