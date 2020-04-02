package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.user.User;
import utils.FXUtils;

public class StudentMenuController {

    @FXML private Label nameLabel;

    @FXML
    private void initialize() {
        // Could cause a null pointer exception if the logged in user is not explicitly set
        this.nameLabel.setText(String.format("%s %s",
                User.getLoggedInUser().getFirstName(), User.getLoggedInUser().getLastName()));
    }

    @FXML
    public void onLogOutClick(ActionEvent event) {
        FXUtils.loadView("Selecteer rol", "/views/RoleSelection.fxml", event);
    }
}
