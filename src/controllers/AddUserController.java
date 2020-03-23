package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.*;
import utils.Utils;

import java.util.UUID;

public class AddUserController {

    @FXML private ComboBox<String> userTypeComboBox;
    @FXML private TextField firstNameTextField;
    @FXML private TextField surnameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextfield;
    @FXML private Button cancelButton;
    @FXML private Button registrerButton;

    @FXML
    private void initialize() {

        ObservableList<String> items = FXCollections.observableArrayList("Docent", "Student");
        userTypeComboBox.setValue("Student");
        userTypeComboBox.setItems(items);
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
        UUID id = Utils.idGenerator();
        if (!emailTextField.getText().equals("") && !passwordTextfield.getText().equals("") && !firstNameTextField.getText().equals("") && !surnameTextField.getText().equals("")) {
            if (userTypeComboBox.getValue().equals("Docent")) {
                Teacher newUser = new Teacher(emailTextField.getText(), passwordTextfield.getText(), firstNameTextField.getText(), surnameTextField.getText(), id);
                User.addUser(newUser);
            }else if (userTypeComboBox.getValue().equals("Student")) {
                Student newUser = new Student(emailTextField.getText(), passwordTextfield.getText(), firstNameTextField.getText(), surnameTextField.getText(), id);
                User.addUser(newUser);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("User created");
            alert.setHeaderText("Gebruiker gecreërd");
            alert.show();
            initialize();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("User not created");
            alert.setHeaderText("Gebruiker niet gecreërd");
            alert.setContentText("Foutieve informatie ingevoerd.");
            alert.show();
        }



    }

}
