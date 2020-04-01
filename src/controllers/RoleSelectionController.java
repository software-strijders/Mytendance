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
    public void handleSelection(ActionEvent event) {
        try {
            UserLoginController userLogin = this.loadUserLogin(event).getController();
            // Matches the UserType with the fxId of the clicked button by removing the fxId's "Button" suffix
            userLogin.setUserType(UserType.valueOf(((Button)event.getSource()).getId()
                    .replace("Button", "").toUpperCase()));
        } catch (IOException exception) {
            FXUtils.showWarning("Het login menu kan niet geladen worden :(", exception);
        } catch (Exception exception) {
            FXUtils.showError("Er is iets goed misgegaan x(", exception);
        }
    }
}
