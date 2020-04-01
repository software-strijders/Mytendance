package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.user.Administrator;
import models.user.UserFactory;
import enums.UserType;
import utils.FXUtils;

public class AddUserController {

    @FXML private ComboBox<UserType> userTypeComboBox;
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextfield;

    @FXML
    private void initialize() {
        // Indien je een Enum[] array wilt omzetten naar een list van strings:
        // userTypeComboBox.setItems(Arrays.stream(UserType.values()).map(UserType::typeName)
        //          .collect(toCollection(FXCollections::observableArrayList)));
        userTypeComboBox.setItems(FXCollections.observableArrayList(UserType.values()));

        // Clear textfields after user creation
        firstNameTextField.clear();
        lastNameTextField.clear();
        emailTextField.clear();
        passwordTextfield.clear();
    }

    @FXML
    public void onCancelButtonClick(ActionEvent event) {
        ((Stage)((Node)event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) {
        if (emailTextField.getText().isEmpty() ||
                passwordTextfield.getText().isEmpty() ||
                firstNameTextField.getText().isEmpty() ||
                lastNameTextField.getText().isEmpty()) {
            // Zie comment over de AlertType in CreateClassWindowController
            FXUtils.showAlert("Gebruiker niet aangemaakt", Alert.AlertType.INFORMATION);
            return;
        }
        try {
            Administrator.addUser(
                    UserFactory.create(firstNameTextField.getText(),
                            lastNameTextField.getText(),
                            emailTextField.getText(),
                            passwordTextfield.getText(),
                            userTypeComboBox.getValue()));
            FXUtils.showAlert("Gebruiker aangemaakt", Alert.AlertType.INFORMATION);
            initialize();
        } catch (IllegalArgumentException exception) {
            // Zie comment over de AlertType in CreateClassWindowController
            FXUtils.showAlert(exception.getMessage(), Alert.AlertType.INFORMATION);
        }
    }
}
