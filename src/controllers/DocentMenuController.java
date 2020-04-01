package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.user.User;
import utils.FXUtils;
import java.io.IOException;

public class DocentMenuController {

    @FXML private Label nameLabel;

    @FXML
    private void initialize() {
        // Could cause a null pointer exception if the logged in user is not explicitly set
        nameLabel.setText(String.format("%s %s", User.getLoggedInUser().getFirstName(), User.getLoggedInUser().getLastName()));
    }

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

    @FXML
    public void logoutButtonOnClick(ActionEvent event) throws IOException {
        FXUtils.loadComponent("Log in", "/views/RoleSelection.fxml", event);
    }
}
