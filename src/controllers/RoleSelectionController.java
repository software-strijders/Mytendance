package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import java.io.IOException;
import utils.Utils;

public class RoleSelectionController {

    private FXMLLoader loadUserLogin(ActionEvent event) throws IOException {
        return Utils.loadComponent("User login", "/views/UserLogin.fxml", event);
    }

    @FXML
    public void handleSelection(ActionEvent event) throws IOException {
        UserLoginController userLogin = loadUserLogin(event).getController();
        userLogin.setUserType(((Button) event.getSource()).getText());
    }
}
