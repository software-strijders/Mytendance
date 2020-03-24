package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.user.Administrator;
import models.user.UserFactory;
import enums.UserType;
import utils.Utils;

public class AddUserController {

    @FXML private ComboBox<UserType> userTypeComboBox;
    @FXML private TextField firstNameTextField;
    @FXML private TextField surnameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextfield;
    @FXML private Button cancelButton;
    @FXML private Button registrerButton;

    @FXML
    private void initialize() {
        ObservableList<UserType> items = FXCollections.observableArrayList(UserType.values());
        userTypeComboBox.setValue(UserType.STUDENT);
        userTypeComboBox.setItems(items);

        // Clear textfields after user creation
        firstNameTextField.setText("");
        surnameTextField.setText("");
        emailTextField.setText("");
        passwordTextfield.setText("");
    }

    @FXML
    public void onCancelButtonClick(ActionEvent event) {
        initialize();
    }

    @FXML
    public void onRegisterButtonClick(ActionEvent event) {
        if (emailTextField.getText().isEmpty() ||
                passwordTextfield.getText().isEmpty() ||
                firstNameTextField.getText().isEmpty() ||
                surnameTextField.getText().isEmpty()) {
            Utils.makeAlert(Alert.AlertType.ERROR, "Gebruiker niet aangemaakt");
            return;
        }

        try {
            Administrator.addUser(
                    UserFactory.create(emailTextField.getText(),
                            passwordTextfield.getText(),
                            firstNameTextField.getText(),
                            surnameTextField.getText(),
                            userTypeComboBox.getValue()));
        } catch (IllegalArgumentException e) {
            Utils.makeAlert(Alert.AlertType.ERROR, e.getMessage());
            return;
        }

        Utils.makeAlert(Alert.AlertType.INFORMATION, "Gebruiker aangemaakt");

        initialize();
    }
}
