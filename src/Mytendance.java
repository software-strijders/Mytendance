import javafx.application.Application;
import javafx.stage.Stage;
import models.user.Administrator;
import models.FieldOfStudy;
import models.user.Student;
import utils.Utils;

public class Mytendance extends Application {

    public static void main(String[] args){
        Student s1 = new Student("Arjen","Norbart","arjen.norbart@student.hu.nl","password");
        Student s2 = new Student("Jort","Willemsen","jort.willemsen@student.hu.nl","p@ssword");
        Student s3 = new Student("Milan","Dol","milan.dol@student.hu.nl","m1lan");
        Student s4 = new Student("Xander","Vedder","xander.vedder@student.hu.nl","sander");
        Student s5 = new Student("Ruben","van den Brink","ruben.brink@student.hu.nl","rururuben");
        Student s6 = new Student("Joeri","Kok","joeri.j.kok@student.hu.nl","c++");

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
        Utils.loadStage("User role selection", "/views/RoleSelection.fxml", stage);
        // Utils.loadStage("Add new user", "/views/AddUser.fxml", stage);
        // Utils.loadStage("Main menu", "/views/Menu.fxml", stage);
        stage.show();
    }
}
