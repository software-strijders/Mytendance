package controllers;

import enums.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.user.User;
import utils.Utils;
import java.io.IOException;

public class UserLoginController {
    @FXML private Label userTypeLabel;
    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private Button loginButton;
    @FXML private Button switchButton;
    private String userType;

    public UserLoginController() {
        this.userType = "User";
    }

    @FXML
    public void initialize() {
        // will sort this mess out later
        //  emailField.focusedProperty().addListener((argument, oldValue, newValue) ->
        //          emailField.setText(emailField.getText().equals("E-mail") ? "" : emailField.getText()));
    }

    private FXMLLoader loadRoleSelection(ActionEvent event) throws IOException {
        return Utils.loadComponent("User role selection",
                "/views/RoleSelection.fxml", event);
    }

    private boolean isInputValid(String email, String password) {
        if (email.isEmpty())
            Utils.showAlert("Het e-mailadres ontbreekt :(", Alert.AlertType.INFORMATION);
        else if (!Utils.isValidEmail(email))
            Utils.showAlert("Het opgegeven e-mailadres is ongeldig :(", Alert.AlertType.INFORMATION);
        else if (password.isEmpty())
            Utils.showAlert("Het wachtwoord ontbreekt :(", Alert.AlertType.INFORMATION);
        else
            return true;

        return false;
    }

    private boolean isLoginSuccessful(String email, String password) {
        for (User user : Utils.getRegisteredUsers(UserType.valueOf(this.userType)))
            if (user.getEmail().equals(email) && user.getPassword().equals(password))
                return true;

        return false;
    }

    @FXML
    public void handleSwitch(ActionEvent event) throws IOException {
        loadRoleSelection(event);
    }

    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (isInputValid(email, password) && isLoginSuccessful(email, password))
            Utils.showAlert("U bent succesvol ingelogd :)", Alert.AlertType.INFORMATION);
        else {
            Utils.showAlert("Het opgegeven e-mailadres of wachtwoord is verkeerd :(",
                    Alert.AlertType.INFORMATION);
            passwordField.clear();
        }
    }

    public void setUserType(String userType) {
        userTypeLabel.setText(userType);
        this.userType = userType.toUpperCase();
    }
}


