package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Class;
import models.FieldOfStudy;
import models.user.Student;
import utils.Utils;

import java.util.ArrayList;
import java.util.UUID;

public class CreateClassWindowController {

    @FXML
    private ComboBox<FieldOfStudy> fieldOfStudy;
    @FXML
    private Spinner<Integer> studyYearNumber;
    @FXML
    private TextField classLetter;
    @FXML
    private Label generatedClassName;
    @FXML
    private ListView<Student> addedStudentsList;
    @FXML
    private ListView<Student> studentList;
    @FXML
    private Button generateClassIDButton;
    @FXML
    private Button moveStudentBackButton;
    @FXML
    private Button addStudentButton;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;


    public void initialize() {
        ObservableList<FieldOfStudy> studyField = FXCollections.observableArrayList(FieldOfStudy.getFieldOfStudies());
        fieldOfStudy.setItems(studyField);

        SpinnerValueFactory<Integer> yearOfStudy = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);
        studyYearNumber.setValueFactory(yearOfStudy);

        classLetter.setPromptText("1 karakter");


        studentList.setItems(FXCollections.observableArrayList(Student.getRegisteredStudents()));
        studentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void onGenerateIdClick(ActionEvent actionEvent) {
        if (fieldOfStudy.getValue() == null || studyYearNumber.getValue() == null || classLetter.getText() == null
                || classLetter.getText().length() != 1 || Utils.isNumeric(classLetter.getText())) {
            Utils.makeAlert(Alert.AlertType.ERROR, "Vul alle velden (correct) in!");
            return;
        }
        generatedClassName.setText(fieldOfStudy.getValue().toString() + "-" + "V" + studyYearNumber.getValue().toString() + classLetter.getText().toUpperCase());
    }

    public void onPutBackClick(ActionEvent actionEvent) {
        if (addedStudentsList.getSelectionModel().getSelectedItem() != null) {
            studentList.getItems().add(addedStudentsList.getSelectionModel().getSelectedItem());
            addedStudentsList.getItems().remove(addedStudentsList.getSelectionModel().getSelectedItem());
        }
    }

    public void onAddUserClick(ActionEvent actionEvent) {
        if (studentList.getSelectionModel().getSelectedItem() != null) {
            addedStudentsList.getItems().add(studentList.getSelectionModel().getSelectedItem());
            studentList.getItems().remove(studentList.getSelectionModel().getSelectedItem());
        }
    }

    public void onConfirmClick(ActionEvent actionEvent) {
        if (generatedClassName.getText() != null) {
            Class c1 = new Class(UUID.randomUUID(), studyYearNumber.getValueFactory().getValue(), classLetter.getText().charAt(0), generatedClassName.getText(),
            fieldOfStudy.getValue(), new ArrayList<>(addedStudentsList.getItems()));
        }
    }

    public void onCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
