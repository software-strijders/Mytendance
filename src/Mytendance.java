import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.user.Administrator;
import models.FieldOfStudy;
import models.user.Student;

import java.util.UUID;
import utils.Utils;

public class Mytendance extends Application {

    public static void main(String[] args){
        Student s1 = new Student("arjen.norbart@student.hu.nl", "password", "Arjen", "Norbart", UUID.randomUUID());
        Student s2 = new Student("jort.willemsen@student.hu.nl", "p@ssword", "Jort", "Willemsen", UUID.randomUUID());
        Student s3 = new Student("milan.dol@student.hu.nl", "m1lan", "Milan", "Dol", UUID.randomUUID());
        Student s4 = new Student("xander.vedder@student.hu.nl", "sander", "Xander", "Vedder", UUID.randomUUID());
        Student s5 = new Student("ruben.brink@student.hu.nl", "rururuben", "Ruben", "van den Brink", UUID.randomUUID());
        Student s6 = new Student("joeri.kok@student.hu.nl", "c++", "Joeri", "Kok", UUID.randomUUID());

        Administrator.addUser(s1);
        Administrator.addUser(s2);
        Administrator.addUser(s3);
        Administrator.addUser(s4);
        Administrator.addUser(s5);
        Administrator.addUser(s6);

        FieldOfStudy f1 = new FieldOfStudy("TICT-SD");
        FieldOfStudy f2 = new FieldOfStudy("TICT-TI");
        FieldOfStudy f3 = new FieldOfStudy("TICT-AI");
        FieldOfStudy f4 = new FieldOfStudy("TICT-CSC");
        FieldOfStudy f5 = new FieldOfStudy("TICT-BIM");

        FieldOfStudy.getFieldOfStudies().add(f1);
        FieldOfStudy.getFieldOfStudies().add(f2);
        FieldOfStudy.getFieldOfStudies().add(f3);
        FieldOfStudy.getFieldOfStudies().add(f4);
        FieldOfStudy.getFieldOfStudies().add(f5);

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Simply comment out the screens you're not currently testing
        Utils.loadScreen("User role selection", "/views/RoleSelection.fxml", stage);
        // Utils.loadScreen("Add new user", "../views/AddUser.fxml", stage);
    }
}
