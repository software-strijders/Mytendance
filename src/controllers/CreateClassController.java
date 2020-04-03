package controllers;

import javafx.collections.FXCollections;
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

    @FXML private TextField searchStudentBar;
    @FXML private ComboBox<FieldOfStudy> fieldOfStudy;
    @FXML private Spinner<Integer> studyYearNumber;
    @FXML private TextField classLetter;
    @FXML private Label generatedClassName;
    @FXML private ListView<Student> addedStudentsList;
    @FXML private ListView<Student> studentList;

    private Teacher teacher;
    private List<Student> allStudents;

    public void initialize() throws IllegalAccessException {
        setUpUser();
        setUpItems();
        setUpListeners();
    }

    private void setUpUser() throws IllegalAccessException {
        // TODO: Util method?
        if (User.getLoggedInUser() instanceof Teacher)
            this.teacher = (Teacher)User.getLoggedInUser();
        else
            throw new IllegalAccessException("Dit scherm is alleen toegankelijk voor docenten!");
    }

    private void setUpItems() {
        this.fieldOfStudy.setItems(FXCollections.observableList(FieldOfStudy.getFieldOfStudies()));
        this.studyYearNumber.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1));
        this.allStudents = Student.getRegisteredStudents();
        this.studentList.setItems(FXCollections.observableList(this.allStudents));

        // Unsure whether or not we use this
        this.studentList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    private void setUpListeners() {
        this.fieldOfStudy.valueProperty().addListener((observableValue, fieldOfStudy, teacher) -> this.showGeneratedName());
        this.studyYearNumber.getEditor().textProperty().addListener((observableValue, oldText, newText) -> this.showGeneratedName());
        this.classLetter.textProperty().addListener((observableValue, oldText, newText) -> {
            if (newText.length() > 1 || Utils.hasSpecial(newText) || Utils.isNumeric(newText)) {
                this.classLetter.setText(oldText);
                return;
            }
            this.showGeneratedName();
        });
    }

    private void showGeneratedName() {
        if (this.fieldOfStudy.getValue() != null
                && this.studyYearNumber.getValue() != null
                && this.classLetter.getText() != null
                && this.classLetter.getText().length() == 1
                && !Utils.isNumeric(this.classLetter.getText()))
            this.generatedClassName.setText(Utils.formatClassName(
                    this.fieldOfStudy.getValue().toString(), this.studyYearNumber.getValue(),
                    Utils.capitalize(this.classLetter.getText()).toCharArray()[0]));
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
        if (this.generatedClassName.getText().isEmpty()
                || this.addedStudentsList.getItems().isEmpty()) {
            FXUtils.showInfo("Niet alle velden zijn ingevuld :(");
            return;
        }
        Class newClass = new Class(this.studyYearNumber.getValueFactory().getValue(),
                Utils.capitalize(this.classLetter.getText()).toCharArray()[0],
                this.fieldOfStudy.getValue(),
                new ArrayList<>(this.addedStudentsList.getItems()));

        if (Class.getAllClasses().contains(newClass)) {
            FXUtils.showInfo("De klas bestaat al :(");
            return;
        }

        this.clearFields();
        this.teacher.addClass(newClass);
        this.addClassToSelectedStudents(newClass);
        Class.addClass(newClass);
        FXUtils.showInfo("De nieuwe klas is toegevoegd :)");
    }

    private void addClassToSelectedStudents(Class newClass) {
        for (Student student : this.addedStudentsList.getItems()) {
            student.addClass(newClass);
        }
    }

    private void clearFields() {
        this.classLetter.clear();
        this.searchStudentBar.clear();
        this.generatedClassName.setText(""); // Can't clear this one, since it's a label
        this.addedStudentsList.setItems(null);
        this.studentList.setItems(FXCollections.observableArrayList(
                Student.getRegisteredStudents()
        ));
        this.fieldOfStudy.getSelectionModel().clearSelection();
        this.studyYearNumber.getValueFactory().valueProperty().setValue(1);
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
