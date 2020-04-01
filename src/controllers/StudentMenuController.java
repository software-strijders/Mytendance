package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.user.User;
import utils.FXUtils;
import java.io.IOException;

public class StudentMenuController {

    @FXML private Label nameLabel;

    @FXML
    private void initialize() {
        nameLabel.setText(String.format("%s %s",
                User.getLoggedInUser().getFirstName(), User.getLoggedInUser().getLastName()));
    }

    private void loadRoleSelection(ActionEvent event) throws IOException {
        FXUtils.loadComponent("Selecteer rol", "/views/RoleSelection.fxml", event);
    }

    @FXML
    public void onLogOutClick(javafx.event.ActionEvent event) {
        try {
            this.loadRoleSelection(event);
        } catch (IOException exception) {
            FXUtils.showWarning("Het rol selectie menu kan niet geladen worden :(", exception);
        } catch (Exception exception) {
            FXUtils.showError("Er is iets goed misgegaan x(", exception);
        }
    }
}
