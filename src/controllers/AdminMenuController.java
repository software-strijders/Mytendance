package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.user.User;
import utils.FXUtils;

public class AdminMenuController {

    @FXML private Label loggedInUserLabel;

    @FXML
    public void initialize() {
        // Could cause a null pointer exception if the logged in user is not explicitly set
        this.loggedInUserLabel.setText(User.getLoggedInUser().toString());
    }

    @FXML
    private void onCreateUserClick(ActionEvent event) {
        FXUtils.loadView("Gebruiker aanmaken", "/views/CreateUser.fxml", false);
    }

    @FXML
    public void onLogOutClick(ActionEvent event) {
        FXUtils.loadView("Rol selecteren", "/views/RoleSelection.fxml", event);
    }
}
