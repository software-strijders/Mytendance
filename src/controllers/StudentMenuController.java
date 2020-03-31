package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import utils.FXUtils;

import java.io.IOException;

public class StudentMenuController {

    @FXML private Button logOutButton;

    @FXML
    public void onLogOutClick(javafx.event.ActionEvent event) throws IOException {
        FXUtils.loadComponent("Rol selectie","/views/RoleSelection.fxml", event);
    }
}
