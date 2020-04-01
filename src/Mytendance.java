import enums.AttendanceType;
import enums.ReasonType;
import enums.SubjectType;
import javafx.application.Application;
import javafx.stage.Stage;
import models.Attendance;
import models.Class;
import models.FieldOfStudy;
import models.Lecture;
import models.user.Administrator;
import models.user.Student;
import models.user.Teacher;
import utils.FXUtils;
import utils.Utils;
import java.time.LocalDate;


public class Mytendance extends Application {

    public static void main(String[] args) {
        Student s1 = new Student("Arjen", "Norbart", "arjen.norbart@student.hu.nl", "password");
        Student s2 = new Student("Jort", "Willemsen", "jort.willemsen@student.hu.nl", "p@ssword");
        Student s3 = new Student("Milan", "Dol", "milan.dol@student.hu.nl", "m1lan");
        Student s4 = new Student("Xander", "Vedder", "xander.vedder@student.hu.nl", "sander");
        Student s5 = new Student("Ruben", "van den Brink", "ruben.brink@student.hu.nl", "rururuben");
        Student s6 = new Student("Joeri", "Kok", "joeri.j.kok@student.hu.nl", "c++");
        Teacher t1 = new Teacher("Henk", "Tank", "Henk@hu.nl", "w8woord");
        Administrator ad1 = new Administrator("Jort", "Willemsen", "jort@admin.nl", "1234");

        Administrator.addUser(s1);
        Administrator.addUser(s2);
        Administrator.addUser(s3);
        Administrator.addUser(s4);
        Administrator.addUser(s5);
        Administrator.addUser(s6);
        Administrator.addUser(t1);
        Administrator.addUser(ad1);

        FieldOfStudy f1 = new FieldOfStudy("TICT-SD");
        FieldOfStudy f2 = new FieldOfStudy("TICT-TI");
        FieldOfStudy f3 = new FieldOfStudy("TICT-AI");
        FieldOfStudy f4 = new FieldOfStudy("TICT-CSC");
        FieldOfStudy f5 = new FieldOfStudy("TICT-BIM");

        Class c1 = new Class(Utils.idGenerator(), 2, 'H', f1, Student.getRegisteredStudents());
        Class c2 = new Class(Utils.idGenerator(), 3, 'E', f1, Student.getRegisteredStudents());

        Lecture l1 = new Lecture(LocalDate.now().atStartOfDay().withHour(10), 120, SubjectType.OOAD, t1, c2);
        Lecture l2 = new Lecture(LocalDate.now().atStartOfDay().withHour(12), 180, SubjectType.OOAD, t1, c2);

        Lecture.addLecture(l1);
        Lecture.addLecture(l2);

        Attendance a1 = new Attendance(ReasonType.DENTIST, "Mijn kies doet kapot veel pijn ouwe", s5, AttendanceType.ABSENT);
        Attendance a2 = new Attendance(ReasonType.ENTOMBMENT, "Ik word binnenkort begraven", s2, AttendanceType.ABSENT);
        Attendance a4 = new Attendance(s1);
        Attendance a5 = new Attendance(s3);
        Attendance a6 = new Attendance(s4);
        Attendance a7 = new Attendance(s6);

        l1.addAttendance(a1);
        l1.addAttendance(a2);
        l1.addAttendance(a4);
        l1.addAttendance(a5);
        l1.addAttendance(a6);
        l1.addAttendance(a7);

        c1.addLecture(l1);
        c1.addLecture(l2);

        t1.addClass(c1);
        t1.addClass(c2);

        FieldOfStudy.addStudyField(f1);
        FieldOfStudy.addStudyField(f2);
        FieldOfStudy.addStudyField(f3);
        FieldOfStudy.addStudyField(f4);
        FieldOfStudy.addStudyField(f5);

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Simply comment out the screens that you're not currently testing
        // Please do not remove this setup, we don't want to keep logging in for every test we run
        // FXUtils.loadStage("Add new user", "/views/AddUser.fxml", stage);
        // User.setLoggedInUser(User.getRegisteredUsers().get(0));
        // FXUtils.loadStage("Main menu", "/views/DocentMenu.fxml", stage);
        // FXUtils.loadStage("Add new lecture", "/views/CreateLecture.fxml", stage);
        FXUtils.loadStage("Selecteer rol", "/views/RoleSelection.fxml", stage);
        stage.show();
    }
}
