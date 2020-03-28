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
import java.util.Collections;
import java.util.UUID;

public class CreateClassWindowController {

    @FXML private ComboBox<FieldOfStudy> fieldOfStudy;
    @FXML private Spinner<Integer> studyYearNumber;
    @FXML private TextField classLetter;
    @FXML private Label generatedClassName;
    @FXML private ListView<Student> addedStudentsList;
    @FXML private ListView<Student> studentList;
    @FXML private Button cancelButton;

    public void initialize() {
        ObservableList<FieldOfStudy> studyField = FXCollections.observableArrayList(FieldOfStudy.getFieldOfStudies());
        fieldOfStudy.setItems(studyField);
        fieldOfStudy.valueProperty().addListener((observableValue, fieldOfStudy, t1) -> showGeneratedName());

        SpinnerValueFactory<Integer> yearOfStudy = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);
        studyYearNumber.setValueFactory(yearOfStudy);
        studyYearNumber.getEditor().textProperty().addListener((observableValue, s, t1) -> showGeneratedName());

        classLetter.setPromptText("1 karakter");
        classLetter.textProperty().addListener((observableValue, s, t1) -> showGeneratedName());

        studentList.setItems(FXCollections.observableList(Student.getRegisteredStudents()));
        studentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void showGeneratedName() {
        if (fieldOfStudy.getValue() == null ||
                studyYearNumber.getValue() == null ||
                classLetter.getText() == null ||
                classLetter.getText().length() != 1 ||
                Utils.isNumeric(classLetter.getText())) {
            // We don't want to show an error here. If not all fields are filled in it would show an error. Instead
            // what we do now, is that we ignore it. Once all fields are filled in, it will generate the Class name.
            return;
        }

        generatedClassName.setText(Utils.formatClassName(fieldOfStudy.getValue().toString(),
                studyYearNumber.getValue(),
                Utils.getCharFromStringByIndex(classLetter.getText(), 0)));
    }

    public void onPutBackClick(ActionEvent event) {
        if (addedStudentsList.getSelectionModel().getSelectedItem() != null) {
            move(studentList, addedStudentsList);
        }
    }

    public void onAddUserClick(ActionEvent event) {
        if (studentList.getSelectionModel().getSelectedItem() != null) {
            move(addedStudentsList, studentList);
        }
    }

    public void move(ListView<Student> left, ListView<Student> right) {
        left.getItems().add(right.getSelectionModel().getSelectedItem());
        right.getItems().remove(right.getSelectionModel().getSelectedItem());
    }

    public void onConfirmClick(ActionEvent event) {
        if (generatedClassName.getText().isEmpty() && addedStudentsList.getItems().isEmpty()) {
            // Warnings en errors zijn eigenlijk bedoeld voor harde systeemfouten, bij foutieve gebruikersinvoer
            // zijn info messages voldoende. Dit komt een stuk vriendelijker over en wordt ook over het algemeen aangeraden
            Utils.showAlert("Vul alle velden (correct) in!", Alert.AlertType.INFORMATION);
            return;
        }

        Class newClass = new Class(UUID.randomUUID(),
                studyYearNumber.getValueFactory().getValue(),
                Utils.getCharFromStringByIndex(classLetter.getText(), 0),
                fieldOfStudy.getValue(),
                Collections.unmodifiableList(addedStudentsList.getItems()));

        if (Class.getAllClasses().contains(newClass)) {
            Utils.showAlert("Klas bestaat al!", Alert.AlertType.INFORMATION); // Zie comment hierboven
            return;
        }

        Class.addClass(newClass);
        Utils.showAlert("Klas toegevoegd.", Alert.AlertType.INFORMATION); // Zie comment hierboven
    }

    public void onCancelClick(ActionEvent event) {
        ((Stage)cancelButton.getScene().getWindow()).close();
    }
}
