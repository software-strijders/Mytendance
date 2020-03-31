package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.user.User;
import utils.FXUtils;

import java.io.IOException;

public class StudentMenuController {

    @FXML private Button logOutButton;
    @FXML private Label nameLabel;

    @FXML
    private void initialize() {
        nameLabel.setText(String.format("%s %s", User.getLoggedInUser().getFirstName(), User.getLoggedInUser().getLastName()));
    }

    @FXML
    public void onLogOutClick(javafx.event.ActionEvent event) throws IOException {
        FXUtils.loadComponent("Rol selectie","/views/RoleSelection.fxml", event);
    }
}
