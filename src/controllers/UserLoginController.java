package controllers;

import enums.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import models.user.Administrator;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;
import utils.Utils;
import java.io.IOException;
import java.util.InputMismatchException;

public class UserLoginController {

    @FXML private TextField emailField;
    @FXML private TextField passwordField;
    @FXML private Label userTypeLabel;
    private String email;
    private String password;
    private UserType userType;
    private User loggedInUser;

    public void setUserType(UserType type) {
        this.userTypeLabel.setText(type.toString());
        this.userType = type;
    }

    private void loadRoleSelection(ActionEvent event) throws IOException {
        FXUtils.loadComponent("Selecteer rol",
                "/views/RoleSelection.fxml", event);
    }

    @FXML
    public void handleSwitchRole(ActionEvent event) {
        try {
            this.loadRoleSelection(event);
        } catch (IOException exception) {
            FXUtils.showWarning("Het rol selectie menu kan niet geladen worden :(", exception);
        } catch (Exception exception) {
            FXUtils.showError("Er is iets goed misgegaan x(", exception);
        }
    }

    private void obtainCredentials() throws InputMismatchException {
        this.email = this.emailField.getText().trim();
        this.password = this.passwordField.getText();

        if (this.email.isEmpty())
            throw new InputMismatchException("Het e-mailadres ontbreekt :(");
        else if (!Utils.isEmailValid(this.email))
            throw new InputMismatchException("Het e-mailadres is ongeldig :(");
        else if (this.password.isEmpty())
            throw new InputMismatchException("Het wachtwoord ontbreekt :(");
    }

    private void logInUser() throws IllegalArgumentException {
        this.loggedInUser = User.authenticateUser(this.email, this.password, this.userType);
        User.setLoggedInUser(this.loggedInUser);
    }

    private void clearPassword() {
        this.passwordField.clear();
        this.password = null;
    }

    private void loadMainMenu(ActionEvent event) throws IOException {
        String resource;

        if (this.loggedInUser instanceof Teacher)
            resource = "/views/DocentMenu.fxml";
        else if (this.loggedInUser instanceof Administrator)
            resource = "/views/AdministratorMenu.fxml";
        else
            // Load student menu by default
            resource = "/views/StudentMenu.fxml";

        FXUtils.loadComponent("Hoofdmenu", resource, event);
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        try {
            this.obtainCredentials();
            this.logInUser();
            this.clearPassword();
            this.loadMainMenu(event);
        } catch (InputMismatchException exception) {
            FXUtils.showInfo(exception.getMessage());
        } catch (IllegalArgumentException exception) {
            this.clearPassword();
            FXUtils.showInfo("Het e-mailadres of wachtwoord is incorrect :(");
        } catch (IOException exception) {
            this.clearPassword();
            FXUtils.showWarning("Het hoofdmenu kan niet geladen worden :(", exception);
        } catch (Exception exception) {
            FXUtils.showError("Er is iets goed misgegaan x(", exception);
        }
    }
}
