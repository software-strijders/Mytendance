package controllers;

import enums.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import java.io.IOException;

import utils.FXUtils;

public class RoleSelectionController {

    private FXMLLoader loadUserLogin(ActionEvent event) throws IOException {
        return FXUtils.loadComponent("Gebruiker login", "/views/UserLogin.fxml", event);
    }

    @FXML
    public void handleSelection(ActionEvent event) throws IOException {

        String userType = "";

        UserLoginController userLogin = loadUserLogin(event).getController();

        switch (((Button) event.getSource()).getId()) {
            case "studentButton":
                userType = "Student"; break;
            case "teacherButton":
                userType = "Docent"; break;
            case "adminButton":
                userType = "Admin"; break;
        }
        userLogin.setUserType(userType);
    }
}
