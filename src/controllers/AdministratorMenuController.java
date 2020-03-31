package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.user.User;
import utils.FXUtils;

import java.io.IOException;

public class AdministratorMenuController {

    @FXML private Button createUserButton;
    @FXML private Button createClassButton;
    @FXML private Button createLectureButton;
    @FXML private Button logOutButton;
    @FXML private Label loggedUserLabel;

    @FXML
    void initialize() {
        loggedUserLabel.setText(String.valueOf(User.getLoggedInUser()));
    }

    @FXML
    void onCreateClassClick(ActionEvent event) throws IOException {
        FXUtils.loadComponent("Klas aanmaken","/views/CreateClassWindow.fxml", event);
    }

    @FXML
    void onCreateLectureClick(ActionEvent event) throws IOException {
        FXUtils.loadComponent("Les aanmaken","/views/CreateLecture.fxml", event);
    }

    @FXML
    void onCreateUserClick(ActionEvent event) throws IOException {
        FXUtils.loadComponent("Gebruiker aanmaken","/views/AddUser.fxml", event);
    }

    public void onLogOutClick(ActionEvent event) throws IOException {
        FXUtils.loadComponent("Rol selectie","/views/RoleSelection.fxml", event);
    }
}
