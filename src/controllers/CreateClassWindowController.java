package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Class;
import models.FieldOfStudy;
import models.user.Student;
import utils.FXUtils;
import utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CreateClassWindowController {

    private List<Student> allStudents;

    @FXML private TextField searchStudentBar;
    @FXML private ComboBox<FieldOfStudy> fieldOfStudy;
    @FXML private Spinner<Integer> studyYearNumber;
    @FXML private TextField classLetter;
    @FXML private Label generatedClassName;
    @FXML private ListView<Student> addedStudentsList;
    @FXML private ListView<Student> studentList;
    @FXML private Button cancelButton;

    public void initialize() {
        this.allStudents = Student.getRegisteredStudents();

        ObservableList<FieldOfStudy> studyField = FXCollections.observableArrayList(FieldOfStudy.getFieldOfStudies());
        this.fieldOfStudy.setItems(studyField);
        this.fieldOfStudy.valueProperty().addListener((observableValue, fieldOfStudy, t1) -> this.showGeneratedName());

        SpinnerValueFactory<Integer> yearOfStudy = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);
        this.studyYearNumber.setValueFactory(yearOfStudy);
        this.studyYearNumber.getEditor().textProperty().addListener((observableValue, s, t1) -> this.showGeneratedName());

        this.classLetter.setPromptText("1 karakter");
        this.classLetter.textProperty().addListener((observableValue, s, t1) -> this.showGeneratedName());

        this.studentList.setItems(FXCollections.observableList(this.allStudents));
        this.studentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void showGeneratedName() {
        if (this.fieldOfStudy.getValue() == null ||
                this.studyYearNumber.getValue() == null ||
                this.classLetter.getText() == null ||
                this.classLetter.getText().length() != 1 ||
                Utils.isNumeric(this.classLetter.getText())) {
            return;
        }
        this.generatedClassName.setText(Utils.formatClassName(this.fieldOfStudy.getValue().toString(),
                this.studyYearNumber.getValue(),
                Utils.getCharFromStringByIndex(this.classLetter.getText(), 0)));
    }

    @FXML
    private void onPutBackClick(ActionEvent event) {
        if (this.addedStudentsList.getSelectionModel().getSelectedItem() != null) {
            this.move(this.addedStudentsList, false);
        }
    }

    @FXML
    private void onAddUserClick(ActionEvent event) {
        if (this.studentList.getSelectionModel().getSelectedItem() != null) {
            this.move(this.studentList, true);
        }
    }

    private void move(ListView<Student> selectedListView, boolean moveToAdded) {
        Student selected = selectedListView.getSelectionModel().getSelectedItem();

        if (moveToAdded) {
            this.addedStudentsList.getItems().add(selected);
            this.allStudents.remove(selected);
        } else {
            this.addedStudentsList.getItems().remove(selected);
            this.allStudents.add(selected);
        }
        this.studentList.setItems(FXCollections.observableList(this.allStudents));
        this.searchStudentBar.clear();
    }

    public void onConfirmClick(ActionEvent event) {
        if (generatedClassName.getText().isEmpty() && addedStudentsList.getItems().isEmpty()) {
            // Warnings en errors zijn eigenlijk bedoeld voor harde systeemfouten, bij foutieve gebruikersinvoer
            // zijn info messages voldoende. Dit komt een stuk vriendelijker over en wordt ook over het algemeen aangeraden
            FXUtils.showAlert("Vul alle velden (correct) in!", Alert.AlertType.INFORMATION);
        }
        Class newClass = new Class(UUID.randomUUID(),
                this.studyYearNumber.getValueFactory().getValue(),
                Utils.getCharFromStringByIndex(this.classLetter.getText(), 0),
                this.fieldOfStudy.getValue(),
                Collections.unmodifiableList(this.addedStudentsList.getItems()));

        if (Class.getAllClasses().contains(newClass)) {
            FXUtils.showAlert("Klas bestaat al!", Alert.AlertType.INFORMATION); // Zie comment hierboven
            return;
        }
        Class.addClass(newClass);
        FXUtils.showAlert("Klas toegevoegd.", Alert.AlertType.INFORMATION); // Zie comment hierboven
    }

    @FXML
    private void onCancelClick(ActionEvent event) {
        ((Stage) this.cancelButton.getScene().getWindow()).close();
    }

    @FXML
    private void onSearchStudentClick(KeyEvent event) {
        String searched = this.searchStudentBar.getText();
        ArrayList<Student> matchedStudents = new ArrayList<>();

        for (Student student : this.allStudents) {
            if (student.toString().toLowerCase().contains(searched.toLowerCase())) {
                matchedStudents.add(student);
            }
        }
        this.studentList.setItems(FXCollections.observableList(matchedStudents));
    }
}
