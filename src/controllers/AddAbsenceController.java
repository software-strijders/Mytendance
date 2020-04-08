package controllers;

import enums.AttendanceType;
import enums.SubjectType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;

public class AddAbsenceController {

    @FXML private ComboBox<AttendanceType> reasonComboBox;
    @FXML private TextArea descriptionBox;
    @FXML private ComboBox<SubjectType> subjectComboBox;
    @FXML private DatePicker datedatePicker;

    @FXML private TableView<Lecture> lectureTableView;
    @FXML private TableColumn<Lecture, SubjectType> subjectColumn;
    @FXML private TableColumn<Lecture, LocalTime> startTimeColumn;
    @FXML private TableColumn<Lecture, Integer> durationColumn;

    private Student loggedInStudent;

    @FXML
    public void initialize() throws IllegalAccessException {
        if (User.getLoggedInUser() instanceof Student) {
            this.loggedInStudent = (Student) User.getLoggedInUser();
        } else {
            throw new IllegalAccessException("U heeft niet de bevoegde rol voor deze informatie :(");
        }
        datedatePicker.setValue(LocalDate.now());
        setUpLectureTable();
        fillSubjectComboBox();
        fillReasonComboBox();
    }

    @FXML
    private void fillSubjectComboBox() {
        this.subjectComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(SubjectType.values())));
    }

    @FXML
    private void fillReasonComboBox() {
        this.reasonComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(AttendanceType.Absent.values())));
    }

    @FXML
    private void onCancelClick(ActionEvent event) {
        FXUtils.loadView("Hoofdmenu", "/views/StudentMenu.fxml", event);
    }

    @FXML
    private void clearFields() {
        datedatePicker.setValue(LocalDate.now());
        subjectComboBox.setValue(SubjectType.OOP);
        reasonComboBox.setValue(AttendanceType.Absent.ILL);
        descriptionBox.clear();
    }

    @FXML
    private void onConfirmClick(ActionEvent event) {
        Lecture selectedLecture = lectureTableView.getSelectionModel().getSelectedItem();
        AttendanceType selectedReason = reasonComboBox.getValue();
        String reasonDescription = descriptionBox.getText();

        try {
            if (reasonDescription.isEmpty()) {
                throw new Exception();
            }
            for (Attendance attendance : selectedLecture.getAttendances()) {
                if (attendance.getStudent().equals(loggedInStudent)) {
                    attendance.setType(selectedReason);
                    attendance.setDescription(reasonDescription);
                    FXUtils.showInfo("De absentiemelding is aangemaakt :)");
                    clearFields();
                }
            }
        } catch (Exception e) {
            FXUtils.showWarning("Fout!", "Vul alle velden in :/");
        }
    }

    @FXML
    private void setUpLectureTable() {
        this.subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        this.startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        this.durationColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    }

    @FXML
    public void onSubjectComboBoxClick(ActionEvent event) {
        SubjectType selectedSubject = subjectComboBox.getValue();
        LocalDate selectedDate = datedatePicker.getValue();
        ArrayList<Lecture> allLectures =  new ArrayList<>();

        for (Class class_ : loggedInStudent.getClasses()) {
            for (Lecture lecture : class_.getLecturesByDate(selectedDate)) {
                if (lecture.getSubject().equals(selectedSubject)) {
                    allLectures.add(lecture);
                }
            }
        }
        lectureTableView.setItems(FXCollections.observableArrayList(allLectures));
    }
}
