package controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.user.Administrator;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;

public class MytendanceController {

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

        FXUtils.loadPaneIntoView(this.FXMLContainer, "/views/AbsenceOverview.fxml");

        this.loggedInUserLabel.setText(this.loggedInUser.toString());
        this.logOutButton.getStyleClass().remove(0); // Remove default styling
    }

    private void setUpVariables() {
        this.children = this.menu.getChildren();
        this.group = new ToggleGroup();
    }

    private void setUpMenuButtons() {
        this.loggedInUser = User.getLoggedInUser();

        if (this.loggedInUser instanceof Administrator)
            this.loadAdministratorButtons();
        else if (this.loggedInUser instanceof Teacher)
            this.loadTeacherButtons();
        else
            this.loadStudentButtons();
    }


    private void loadAdministratorButtons() {
        FXUtils.loadButtonComponent("Gebruiker aanmaken",
                event -> FXUtils.loadPaneIntoView(this.FXMLContainer, "/views/CreateUser.fxml"), 0, this.children, this.group);
    }

    private void loadTeacherButtons() {
        FXUtils.loadButtonComponent("Overzicht Lessen",
                event -> FXUtils.loadPaneIntoView(this.FXMLContainer, "/views/TeacherShowLectures.fxml"), 0, this.children, this.group);
        FXUtils.loadButtonComponent("Presentie doen",
                event -> FXUtils.loadPaneIntoView(this.FXMLContainer, "/views/TakeAttendance.fxml"), 0, this.children, this.group);
        FXUtils.loadButtonComponent("Les aanmaken",
                event -> FXUtils.loadPaneIntoView(this.FXMLContainer, "/views/CreateLecture.fxml"), 0, this.children, this.group);
        FXUtils.loadButtonComponent("Klas aanmaken",
                event -> FXUtils.loadPaneIntoView(this.FXMLContainer, "/views/CreateClass.fxml"), 0, this.children, this.group);
        FXUtils.loadButtonComponent("Gebruiker aanmaken",
                event -> FXUtils.loadPaneIntoView(this.FXMLContainer, "/views/CreateUser.fxml"), 0, this.children, this.group);
    }

    private void loadStudentButtons() {
        FXUtils.loadButtonComponent("Overzicht lessen",
                event -> FXUtils.loadPaneIntoView(this.FXMLContainer, "/views/StudentLectureOverview.fxml"), 0, this.children, this.group);
        FXUtils.loadButtonComponent("Absentie toevoegen",
                event -> FXUtils.loadPaneIntoView(this.FXMLContainer, "/views/AddAbsence.fxml"), 0, this.children, this.group);
    }

    @FXML
    private void onLogOutClicked(ActionEvent event) {
        FXUtils.loadView("Hoofdmenu", "/views/RoleSelection.fxml", event);
    }
}
