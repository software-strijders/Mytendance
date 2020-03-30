package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.FXUtils;
import java.io.IOException;

public class MenuController {

    @FXML
    public void onUserCreatePressed(ActionEvent event) throws IOException {
        Stage stage = FXUtils.loadStage("Voeg gebruiker toe",
                "/views/AddUser.fxml", Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    public void onClassCreateButtonPressed(ActionEvent event) throws IOException {
        Stage stage = FXUtils.loadStage("Klas aanmaken",
                "/views/CreateClassWindow.fxml", Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    public void onDoAttendanceClick(ActionEvent event) throws IOException {
        Stage newStage = FXUtils.loadStage("Presentie doen","/views/DoAttendance.fxml",  Modality.APPLICATION_MODAL);
        newStage.showAndWait();
    }

    @FXML
    public void onCreateLectureClick(ActionEvent event) throws IOException {
        Stage newStage = FXUtils.loadStage("Les aanmaken", "/views/CreateLecture.fxml", Modality.APPLICATION_MODAL);
        newStage.showAndWait();
    }
}
