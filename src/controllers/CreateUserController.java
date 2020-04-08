package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.user.Administrator;
import models.user.UserFactory;
import enums.UserType;
import utils.FXUtils;
import utils.Utils;
import java.util.InputMismatchException;

public class CreateUserController {

    @FXML private ComboBox<UserType> userTypeComboBox;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextfield;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @FXML
    private void initialize() {
        this.userTypeComboBox.setItems(FXCollections.observableArrayList(UserType.values()));
        this.clearInput();
    }

    private void obtainUserInfo() throws InputMismatchException {
        this.firstName = this.firstNameTextField.getText().trim();
        this.lastName = this.lastNameTextField.getText().trim();
        this.email = this.emailTextField.getText().trim();
        this.password = this.passwordTextfield.getText();

        if (this.firstName.isEmpty())
            throw new InputMismatchException("De voornaam ontbreekt :(");
        else if (this.lastName.isEmpty())
            throw new InputMismatchException("De achternaam ontbreekt :(");
        else if (this.email.isEmpty())
            throw new InputMismatchException("Het e-mailadres ontbreekt :(");
        else if (!Utils.isEmailValid(this.email))
            throw new InputMismatchException("Het e-mailadres is ongeldig :(");
        else if (this.password.isEmpty())
            throw new InputMismatchException("Het wachtwoord ontbreekt :(");
    }

    private void createUser() throws IllegalArgumentException {
        Administrator.addUser(UserFactory.create(this.firstName, this.lastName,
                this.email, this.password, this.userTypeComboBox.getValue()));
    }

    private void clearInput() {
        this.firstNameTextField.clear();
        this.lastNameTextField.clear();
        this.emailTextField.clear();
        this.clearPassword();
    }

    private void clearPassword() {
        this.passwordTextfield.clear();
        this.password = null;
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) {
        try {
            this.obtainUserInfo();
            this.createUser();
            this.clearInput();
            FXUtils.showInfo("Nieuwe gebruiker aangemaakt :)");
        } catch (InputMismatchException | IllegalArgumentException exception) {
            this.clearPassword();
            FXUtils.showInfo(exception.getMessage());
        }
    }
}
