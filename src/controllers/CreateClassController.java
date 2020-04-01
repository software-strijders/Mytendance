package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import models.Class;
import models.FieldOfStudy;
import models.user.Student;
import models.user.Teacher;
import models.user.User;
import utils.FXUtils;
import utils.Utils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreateClassController {

    private List<Student> allStudents;

    @FXML private TextField searchStudentBar;
    @FXML private ComboBox<FieldOfStudy> fieldOfStudy;
    @FXML private Spinner<Integer> studyYearNumber;
    @FXML private TextField classLetter;
    @FXML private Label generatedClassName;
    @FXML private ListView<Student> addedStudentsList;
    @FXML private ListView<Student> studentList;

    public void initialize() {
        this.allStudents = Student.getRegisteredStudents();

        this.fieldOfStudy.setItems(FXCollections.observableList(FieldOfStudy.getFieldOfStudies()));
        this.fieldOfStudy.valueProperty().addListener((observableValue, fieldOfStudy, teacher) -> this.showGeneratedName());

        this.studyYearNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1));
        this.studyYearNumber.getEditor().textProperty().addListener((observableValue, student, teacher) -> this.showGeneratedName());

        this.classLetter.textProperty().addListener((observableValue, student, teacher) -> this.showGeneratedName());

        this.studentList.setItems(FXCollections.observableList(this.allStudents));
        this.studentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void showGeneratedName() {
        if (this.fieldOfStudy.getValue() != null
                && this.studyYearNumber.getValue() != null
                && this.classLetter.getText() != null
                && this.classLetter.getText().length() == 1
                && !Utils.isNumeric(this.classLetter.getText()))
            this.generatedClassName.setText(Utils.formatClassName(
                    this.fieldOfStudy.getValue().toString(), this.studyYearNumber.getValue(),
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
        if (generatedClassName.getText().isEmpty()
                || addedStudentsList.getItems().isEmpty()) {
            FXUtils.showInfo("Niet alle velden zijn ingevuld :(");
            return;
        }
        Class newClass = new Class(this.studyYearNumber.getValueFactory().getValue(),
                Utils.getCharFromStringByIndex(this.classLetter.getText(), 0),
                this.fieldOfStudy.getValue(),
                new ArrayList<>(this.addedStudentsList.getItems()));

        if (Class.getAllClasses().contains(newClass)) {
            FXUtils.showInfo("De klas bestaat al :(");
            return;
        }
        if (User.getLoggedInUser() instanceof Teacher) {
            ((Teacher)User.getLoggedInUser()).addClass(newClass);
        }
        Class.addClass(newClass);
        FXUtils.showInfo("De nieuwe klas is toegevoegd :)");
    }

    @FXML
    private void onSearchStudentClick(KeyEvent event) {
        String searchQuery = this.searchStudentBar.getText();
        List<Student> matchedStudents = new ArrayList<>();

        for (Student student : this.allStudents) {
            if (student.toString().toLowerCase().contains(searchQuery.toLowerCase())) {
                matchedStudents.add(student);
            }
        }
        this.studentList.setItems(FXCollections.observableList(matchedStudents));
    }

    @FXML
    private void onCancelClick(ActionEvent event) {
        FXUtils.closeStage(event);
    }
}
