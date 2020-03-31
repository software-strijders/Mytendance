package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.user.Administrator;
import models.user.UserFactory;
import enums.UserType;
import utils.FXUtils;

import java.util.ArrayList;

public class AddUserController {

    @FXML private ComboBox<String> userTypeComboBox;
    @FXML private TextField firstNameTextField;
    @FXML private TextField surnameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextfield;

    @FXML
    private void initialize() {
        userTypeComboBox.setPromptText("Gebruikerstype");
        ArrayList<String> userTypes = new ArrayList<>();
        for (UserType userType : UserType.values()) {
            userTypes.add(userType.typeName());
        }
        userTypeComboBox.setItems(FXCollections.observableArrayList(userTypes));

        // Clear textfields after user creation
        firstNameTextField.clear();
        surnameTextField.clear();
        emailTextField.clear();
        passwordTextfield.clear();
    }

    @FXML
    public void onCancelButtonClick(ActionEvent event) {
        ((Stage) surnameTextField.getScene().getWindow()).close();
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) {


        if (emailTextField.getText().isEmpty() ||
                passwordTextfield.getText().isEmpty() ||
                firstNameTextField.getText().isEmpty() ||
                surnameTextField.getText().isEmpty()) {
            // Zie comment over de AlertType in CreateClassWindowController
            FXUtils.showAlert("Gebruiker niet aangemaakt", Alert.AlertType.INFORMATION);
            return;
        }
        try {
            Administrator.addUser(
                    UserFactory.create(emailTextField.getText(),
                            passwordTextfield.getText(),
                            firstNameTextField.getText(),
                            surnameTextField.getText(),
                            getUserTypeFromComboBox()));
            FXUtils.showAlert("Gebruiker aangemaakt", Alert.AlertType.INFORMATION);
            initialize();
        } catch (IllegalArgumentException exception) {
            // Zie comment over de AlertType in CreateClassWindowController
            FXUtils.showAlert(exception.getMessage(), Alert.AlertType.INFORMATION);
        }
    }

    private UserType getUserTypeFromComboBox() {
        switch (userTypeComboBox.getValue()) {
            case "Administrator":
                return UserType.ADMIN;
            case "Docent":
                return UserType.TEACHER;
            default:
                return UserType.STUDENT;
        }
    }
}
