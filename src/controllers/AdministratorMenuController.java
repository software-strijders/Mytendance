package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.user.User;
import utils.FXUtils;

import java.io.IOException;

public class AdministratorMenuController {

    @FXML private Label loggedUserLabel;

    @FXML
    public void initialize() {
        loggedUserLabel.setText(String.valueOf(User.getLoggedInUser()));
    }

    @FXML
    private void onCreateUserClick(ActionEvent event) throws IOException {
        FXUtils.loadComponent("Gebruiker aanmaken", "/views/AddUser.fxml", event);
    }

    @FXML
    public void onLogOutClick(ActionEvent event) throws IOException {
        FXUtils.loadComponent("Rol selectie", "/views/RoleSelection.fxml", event);
    }
}
