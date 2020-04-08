package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.user.Administrator;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;

public class MytendanceController {

    @FXML private VBox background;
    @FXML private Label loggedInUserLabel;
    @FXML private Button logOutButton;
    @FXML private HBox menu;
    @FXML private AnchorPane FXMLContainer;

    private User loggedInUser;
    private ObservableList<Node> children;
    private ToggleGroup group;

    @FXML
    private void initialize() {
        this.setUpVariables();
        this.setUpMenuButtons();

        this.loggedInUserLabel.setText(this.loggedInUser.toString());
        this.logOutButton.getStyleClass().remove(0); // Remove default styling
    }

    private void setUpVariables() {
        this.children = this.menu.getChildren();
        this.group = new ToggleGroup();
    }

    private void setUpMenuButtons() {
        this.loggedInUser = User.getLoggedInUser();

        if (this.loggedInUser instanceof Administrator) {
            this.loadAdministratorButtons();
            this.background.setStyle("-fx-background-image: url(\"/views/style/images/BackgroundGeneral.png\")");
        } else if (this.loggedInUser instanceof Teacher) {
            this.loadTeacherButtons();
            this.background.setStyle("-fx-background-image: url(\"/views/style/images/BackgroundTeacher.png\")");
        } else {
            this.loadStudentButtons();
            this.background.setStyle("-fx-background-image: url(\"/views/style/images/BackgroundStudent.png\")");
        }
    }


    private void loadAdministratorButtons() {
        FXUtils.loadButtonComponent("Home", event -> this.loadMainView(
                "/views/CreateUser.fxml"), 0, this.children, this.group, true);
        FXUtils.loadButtonComponent("Gebruikers overzicht", event -> this.loadMainView(
                "/views/UserOverview.fxml"), 0, this.children, this.group);
        FXUtils.loadToggleButtonComponent("Darkmode", event -> FXUtils.toggleDarkMode(this.background,
                "/views/style/darkmode.css"), 0, this.children);


        this.loadMainView("/views/CreateUser.fxml");
    }

    private void loadTeacherButtons() {
        FXUtils.loadButtonComponent("Home", event -> this.loadMainView(
                "/views/TakeAttendance.fxml"), 0, this.children, this.group, true);
        FXUtils.loadButtonComponent("Statistiek", event -> FXUtils.loadPaneIntoView(this.FXMLContainer,
                "/views/AttendanceOverview.fxml"), 0, this.children, this.group);
        FXUtils.loadButtonComponent("Overzicht Lessen", event -> FXUtils.loadPaneIntoView(this.FXMLContainer,
                "/views/TeacherLectureOverview.fxml"), 0, this.children, this.group);
        FXUtils.loadButtonComponent("Les aanmaken", event -> FXUtils.loadPaneIntoView(this.FXMLContainer,
                "/views/CreateLecture.fxml"), 0, this.children, this.group);
        FXUtils.loadButtonComponent("Klas aanmaken", event -> FXUtils.loadPaneIntoView(this.FXMLContainer,
                "/views/CreateClass.fxml"), 0, this.children, this.group);
        FXUtils.loadToggleButtonComponent("Darkmode", event -> FXUtils.toggleDarkMode(this.background,
                "/views/style/darkmode.css"), 0, this.children);

        this.loadMainView("/views/TakeAttendance.fxml");
    }

    private void loadStudentButtons() {
        FXUtils.loadButtonComponent("Home", event -> this.loadMainView(
                "/views/AddAbsence.fxml"), 0, this.children, this.group, true);
        FXUtils.loadButtonComponent("Absentie aanpassen", event -> FXUtils.loadPaneIntoView(this.FXMLContainer,
                "/views/AdjustAttendance.fxml"), 0, this.children, this.group);
        FXUtils.loadButtonComponent("Overzicht lessen", event -> FXUtils.loadPaneIntoView(this.FXMLContainer,
                "/views/StudentLectureOverview.fxml"), 0, this.children, this.group);
        FXUtils.loadToggleButtonComponent("Darkmode", event -> FXUtils.toggleDarkMode(this.background,
                "/views/style/darkmode.css"), 0, this.children);

        this.loadMainView("/views/AddAbsence.fxml");
    }

    @FXML
    private void onLogOutClicked(ActionEvent event) {
        FXUtils.loadView("Hoofdmenu", "/views/RoleSelection.fxml", event);
    }

    private void loadMainView(String resource) {
        FXUtils.loadPaneIntoView(this.FXMLContainer, resource);
    }
}
