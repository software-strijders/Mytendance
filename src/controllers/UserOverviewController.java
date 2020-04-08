package controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.user.User;
import utils.FXUtils;

import java.util.List;
import java.util.UUID;

public class UserOverviewController {

    @FXML private Button deleteUserButton;
    @FXML private Button changePasswordButton;
    @FXML private TextField newPasswordTextBox;
    @FXML private TableView<User> usersTableView;
    @FXML private TableColumn<User, UUID> idColumn;
    @FXML private TableColumn<User, String> firstNameColumn;
    @FXML private TableColumn<User, String> lastNameColumn;
    @FXML private TableColumn<User, String> emailColumn;

    @FXML
    private void initialize() {

        setupTableView();
        updateTableView();
    }

    private void setupTableView() {
        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        this.firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void updateTableView() {
        usersTableView.setItems(FXCollections.observableArrayList(User.getRegisteredUsers()));
    }

    @FXML
    private void onDeleteUserButtonAction(ActionEvent event) {

        if (usersTableView.getSelectionModel().getSelectedItem() == null) {
            FXUtils.showInfo("Geen gebruiker geselecteerd.");
            return;
        }

        User toBeRemovedUser = usersTableView.getSelectionModel().getSelectedItem();
        Integer toBeRemovedUserIndex = null;
        for (User user : User.getRegisteredUsers()) {
            if (toBeRemovedUser.equals(user)) {
                toBeRemovedUserIndex = User.getRegisteredUsers().indexOf(user);
            }
        }

        if (toBeRemovedUserIndex == null) {
            FXUtils.showInfo("Gebruiker niet gevonden :(");
            return;
        }

        User.removeUser(toBeRemovedUserIndex);
        FXUtils.showInfo("Gebruiker verwijderd.");
        updateTableView();
    }

    @FXML
    private void onChangePasswordButtonClick(ActionEvent event) {
        User user = usersTableView.getSelectionModel().getSelectedItem();

        if(newPasswordTextBox.getText().equals("")){
            FXUtils.showInfo("Voer een geldig wachtwoord in.");
            return;
        }

        user.setPassword(newPasswordTextBox.getText());
        FXUtils.showInfo("Wachtwoord veranderd :) \n Het nieuwe wachtwoord is: " + newPasswordTextBox.getText());
    }
}
