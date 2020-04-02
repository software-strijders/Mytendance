package controllers;

import enums.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import utils.FXUtils;

public class RoleSelectionController {

    @FXML
    public void handleSelection(ActionEvent event) {
        FXMLLoader loginView = FXUtils.loadView("Gebruiker login", "/views/UserLogin.fxml", event);

        if (loginView != null)
            // Matches the UserType with the fxId of the clicked button by removing the fxId's "Button" suffix
            ((UserLoginController)loginView.getController()).setUserType(UserType.valueOf(
                    ((Button)event.getSource()).getId().replace("Button", "").toUpperCase()));
    }
}
