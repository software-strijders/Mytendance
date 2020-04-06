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
    public void onTakeAttendanceClick(ActionEvent event) {
        FXUtils.loadView("Presentie opnemen", "/views/TakeAttendance.fxml", true);
    }

    @FXML
    public void onAttendanceOverviewClick(ActionEvent event) {
        FXUtils.loadView("Aanwezigheid overzicht", "/views/AttendanceOverview.fxml", true);
    }

    @FXML
    public void onCreateLectureClick(ActionEvent event) {
        FXUtils.loadView("Les aanmaken", "/views/CreateLecture.fxml", true);
    }

    @FXML
    public void onShowLecturesViewPressed(ActionEvent actionEvent) {
        FXUtils.loadView("Overzicht Lessen", "/views/TeacherShowLectures.fxml", true);
    }

    @FXML
    public void onCreateClassClick(ActionEvent event) {
        FXUtils.loadView("Klas aanmaken", "/views/CreateClass.fxml", true);
    }

    @FXML
    public void onLogOffClick(ActionEvent event) {
        FXUtils.loadView("Inloggen", "/views/RoleSelection.fxml", event);
    }
}
