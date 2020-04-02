package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.user.User;
import utils.FXUtils;

public class TeacherMenuController {

    @FXML private Label nameLabel;

    @FXML
    private void initialize() {
        // Could cause a null pointer exception if the logged in user is not explicitly set
        this.nameLabel.setText(String.format("%s %s",
                User.getLoggedInUser().getFirstName(), User.getLoggedInUser().getLastName()));
    }

    @FXML
    public void onUserCreatePressed(ActionEvent event) {
        FXUtils.loadView("Gebruiker aanmaken", "/views/CreateUser.fxml", true);
    }

    @FXML
    public void onClassCreateButtonPressed(ActionEvent event) {
        FXUtils.loadView("Klas aanmaken", "/views/CreateClass.fxml", true);
    }

    @FXML
    public void onDoAttendanceClick(ActionEvent event) {
        FXUtils.loadView("Presentie doen", "/views/TakeAttendance.fxml", true);
    }

    @FXML
    public void onCreateLectureClick(ActionEvent event) {
        FXUtils.loadView("Les aanmaken", "/views/CreateLecture.fxml", true);
    }

    @FXML
    public void logoutButtonOnClick(ActionEvent event) {
        FXUtils.loadView("Log in", "/views/RoleSelection.fxml", event);
    }
}
