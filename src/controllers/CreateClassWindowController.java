package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

        studentList.setItems(FXCollections.observableArrayList(Student.getRegisteredStudents()));
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

    public void onPutBackClick(ActionEvent actionEvent) {
        if (addedStudentsList.getSelectionModel().getSelectedItem() != null) {
            move(studentList, addedStudentsList);
        }
    }

    public void onAddUserClick(ActionEvent actionEvent) {
        if (studentList.getSelectionModel().getSelectedItem() != null) {
            move(addedStudentsList, studentList);
        }
    }

    public void move(ListView<Student> left, ListView<Student> right) {
        left.getItems().add(right.getSelectionModel().getSelectedItem());
        right.getItems().remove(right.getSelectionModel().getSelectedItem());
    }

    public void onConfirmClick(ActionEvent actionEvent) {
        if (generatedClassName.getText().isEmpty() && addedStudentsList.getItems().isEmpty()) {
            Utils.makeAlert(Alert.AlertType.ERROR, "Vul alle velden (correct) in!");
            return;
        }

        Class newClass = new Class(UUID.randomUUID(),
                studyYearNumber.getValueFactory().getValue(),
                Utils.getCharFromStringByIndex(classLetter.getText(), 0),
                fieldOfStudy.getValue(),
                new ArrayList<>(addedStudentsList.getItems()));

        if (Class.getAllClasses().contains(newClass)) {
            Utils.makeAlert(Alert.AlertType.ERROR, "Klas bestaat al!");
            return;
        }

        Class.getAllClasses().add(newClass);
        Utils.makeAlert(Alert.AlertType.INFORMATION, "Klas toegevoegd.");
    }

    public void onCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
