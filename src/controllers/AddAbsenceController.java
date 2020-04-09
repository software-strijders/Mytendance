package controllers;

import enums.AttendanceType;
import enums.SubjectType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Attendance;
import models.Class;
import models.Lecture;
import models.user.Student;
import models.user.User;
import utils.FXUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class AddAbsenceController {

    @FXML private ComboBox<AttendanceType> absenceComboBox;
    @FXML private TextArea descriptionBox;
    @FXML private ComboBox<SubjectType> subjectComboBox;
    @FXML private DatePicker datedatePicker;
    @FXML private TableView<Lecture> lectureTableView;
    @FXML private TableColumn<Lecture, SubjectType> subjectColumn;
    @FXML private TableColumn<Lecture, LocalTime> startTimeColumn;
    @FXML private TableColumn<Lecture, Integer> durationColumn;
    @FXML private CheckBox statusCheckbox;

    private Student student;
    private Lecture selectedLecture;
    private AttendanceType selectedAbsence;
    private String description;

    @FXML
    public void initialize() {
        try {
            this.setUpUser();
            this.setUpStatus();
            this.setUpLectureTable();
            this.datedatePicker.setValue(LocalDate.now());
            this.fillSubjectComboBox();
            this.fillReasonComboBox();
        } catch (IllegalAccessException exception) {
            FXUtils.showInfo(exception.getMessage());
        } catch (Exception exception) {
            FXUtils.showError(exception);
        }
    }

    private void setUpUser() throws IllegalAccessException {
        if (User.getLoggedInUser() instanceof Student) {
            this.student = (Student)User.getLoggedInUser();
        } else
            throw new IllegalAccessException("Dit scherm is alleen toegankelijk voor studenten :(");
    }

    private void setUpStatus() {
        this.statusCheckbox.setSelected(this.student.isSick());
    }

    private void setUpLectureTable() {
        this.subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        this.startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        this.durationColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

    @FXML
    private void fillSubjectComboBox() {
        this.subjectComboBox.setItems(
                FXCollections.observableArrayList(Arrays.asList(SubjectType.values())));
    }

    @FXML
    private void fillReasonComboBox() {
        this.absenceComboBox.setItems(
                FXCollections.observableArrayList(Arrays.asList(AttendanceType.Absent.values())));
    }

    private void clearFields() {
        this.datedatePicker.setValue(LocalDate.now());
        this.subjectComboBox.setValue(null);
        this.absenceComboBox.setValue(null);
        this.descriptionBox.clear();
    }

    private void obtainInformation() throws InputMismatchException {
        this.selectedLecture = this.lectureTableView.getSelectionModel().getSelectedItem();
        this.selectedAbsence = this.absenceComboBox.getValue();
        this.description = this.descriptionBox.getText();

        if (this.selectedLecture == null)
            throw new InputMismatchException("Er is geen les geselecteerd :(");
        else if (this.selectedAbsence == null)
            throw new InputMismatchException("Er is geen absentiereden geselecteerd :(");
        else if (this.description.isEmpty())
            throw new InputMismatchException("Er is geen omschrijving opgegeven :(");
    }

    @FXML
    public void onSubjectComboBoxClick(ActionEvent event) {
        SubjectType selectedSubject = this.subjectComboBox.getValue();
        LocalDate selectedDate = this.datedatePicker.getValue();
        List<Lecture> lectures =  new ArrayList<>();

        for (Class myClass : this.student.getClasses())
            lectures.addAll(myClass.getLecturesBySubjectAndDate(selectedSubject, selectedDate));

        this.lectureTableView.setItems(FXCollections.observableArrayList(lectures));
    }

    @FXML
    private void onConfirmClick(ActionEvent event) {
        try {
            this.obtainInformation();
            Attendance attendance = this.selectedLecture.getAttendanceOfStudent(this.student);
            attendance.setType(this.selectedAbsence);
            attendance.setDescription(this.description);
            FXUtils.showInfo("De nieuwe absentiemelding is aangemaakt :)");
            this.clearFields();
        } catch (InputMismatchException exception) {
            FXUtils.showInfo(exception.getMessage());
        } catch (NullPointerException exception) {
            FXUtils.showInfo("Deze les is niet voor jou bedoeld :(");
        } catch (Exception exception) {
            FXUtils.showError(exception);
        }
    }

    @FXML
    private void onStatusCheckBoxClick(ActionEvent event) {
        this.student.setUpcomingAttendances(this.statusCheckbox.isSelected());
        FXUtils.showInfo(String.format("Je bent voor de komende lessen %s",
                this.student.isSick() ? "ziek gemeld (=_= )" : "weer aangemeld :)"));
    }
}
