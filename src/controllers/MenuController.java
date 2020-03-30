package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.Utils;
import java.io.IOException;

public class MenuController {

    @FXML
    public void onUserCreatePressed(ActionEvent event) throws IOException {
        Stage stage = Utils.loadStage("Voeg gebruiker toe",
                "/views/AddUser.fxml", Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    public void onClassCreateButtonPressed(ActionEvent event) throws IOException {
        Stage stage = Utils.loadStage("Klas aanmaken",
                "/views/CreateClassWindow.fxml", Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    public void onDoAttendanceClick(ActionEvent event) throws IOException {
        Stage newStage = Utils.loadStage("Presentie doen","/views/DoAttendance.fxml",  Modality.APPLICATION_MODAL);
        newStage.showAndWait();
    }

    @FXML
    public void onCreateLectureClick(ActionEvent event) throws IOException {
        Stage newStage = Utils.loadStage("Les aanmaken", "/views/CreateLecture.fxml", Modality.APPLICATION_MODAL);
        newStage.showAndWait();
    }
}
