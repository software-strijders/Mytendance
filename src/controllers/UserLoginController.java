package controllers;

import enums.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.user.User;
import utils.Utils;
import java.io.IOException;
import java.util.InputMismatchException;

public class UserLoginController {

    @FXML private Label userTypeLabel;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private Button loginButton;
    @FXML private Button switchButton;
    private String email;
    private String password;
    private UserType userType;

    public void setUserType(String userType) {
        this.userTypeLabel.setText(userType);
        this.userType = UserType.valueOf(userType.toUpperCase());
    }

    private void loadRoleSelection(ActionEvent event) throws IOException {
        Utils.loadComponent("User role selection",
                "/views/RoleSelection.fxml", event);
    }

    @FXML
    public void handleSwitch(ActionEvent event) throws IOException {
        this.loadRoleSelection(event);
    }

    private void obtainCredentials() throws InputMismatchException {
        this.email = emailField.getText().trim();
        this.password = passwordField.getText();

        if (this.email.isEmpty())
            throw new InputMismatchException("Het e-mailadres ontbreekt :(");
        else if (!Utils.isEmailValid(this.email))
            throw new InputMismatchException("Het e-mailadres is ongeldig :(");
        else if (this.password.isEmpty())
            throw new InputMismatchException("Het wachtwoord ontbreekt :(");
    }

    private void logonUser() throws IllegalArgumentException {
        User.setLoggedInUser(User.authenticateUser(this.email, this.password, this.userType));
    }

    private void clearPassword() {
        this.password = null;
    }

    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        try {
            this.obtainCredentials();
            this.logonUser();
        } catch (InputMismatchException exception) {
            Utils.showAlert(exception.getMessage(), Alert.AlertType.INFORMATION);
            return;
        } catch (IllegalArgumentException exception) {
            Utils.showAlert("Het e-mailadres of wachtwoord is incorrect :(",
                    Alert.AlertType.INFORMATION);
            this.passwordField.clear();
            return;
        }
        this.clearPassword();
        Utils.showAlert("U bent succesvol ingelogd :)", Alert.AlertType.INFORMATION);
    }
}
